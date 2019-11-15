import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class ShowtimeManager {
	// Attributes
    // HashMap of showtimeID to showtime object
	private Map <String, Showtime> showtimes = new HashMap<>();

	private Scanner sc = new Scanner(System.in);

    private static ShowtimeManager single_instance = null;

    public static ShowtimeManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ShowtimeManager();
        return single_instance;
    }
    private ShowtimeManager() {
        Map <String, Showtime> showtimeMap = this.load();
        if (showtimeMap != null) {
            this.showtimes = showtimeMap;
        }
    }




	////////////////////////////////////////////////////////////////////////////////////////////
    
    // Still editing
    // Must pass control over to other managers
    // Do not keep the checking within here, only keep the logic
    // How should we handle scanners? Pass it on and on or create in each class
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    

    
    // Public exposed methods to app
    public void getMovieShowtimes(String movieID, String appType) {
        List<Showtime> relevantShowtimes = new ArrayList<Showtime>();

        for (Map.Entry showtimeElement : showtimes.entrySet()) {
            Showtime relevantShowtime = (Showtime) showtimeElement.getValue();
            if (relevantShowtime.getMovieID().equals(movieID)) {
                relevantShowtimes.add(relevantShowtime);
            }
        }

        int choice = 0; // required to initialise
        do {
            System.out.println("These are some details for the showtimes you are looking for: ");
            int j;
            if (relevantShowtimes.size() == 0) {
                System.out.println("No showtimes found");
            } else {
                for (j = 0; j < relevantShowtimes.size(); j++) {
                    System.out.println("Showtime " + (j + 1) + ": showtimeID = " + relevantShowtimes.get(j).getShowtimeID());
                    System.out.println(relevantShowtimes.get(j).getCineplexName() + ", Cinema " + relevantShowtimes.get(j).getCinema().getCinemaID() + ", Hall No. " + relevantShowtimes.get(j).getCinema().getHallNo());
                    System.out.println("Movie Format: " + relevantShowtimes.get(j).getMovieFormat() + "          Date/Time: " + relevantShowtimes.get(j).getDateTime());
                }
            }

            if (appType.equalsIgnoreCase("staff")) {
                System.out.println("==================== SHOWTIMES  ====================\n" +
                        "| 1. View/Update/Remove Specific Showtime          |\n" +
                        "| 2. Create New Showtime                           |\n" +
                        "| 0. Back to MovieManager                          |\n" +
                        "====================================================");
                System.out.println("Enter choice:");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        this.showtimeMenuStaff();
                        break;
                    case 2:
                        this.createShowtime();
                        break;
                    case 0:
                        System.out.println("Back to Showtimes List......");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose between 0-1");
                        break;
                }

            }
        } while (choice != 0);
    }
    
    
    private void showtimeMenuStaff() {
        int choice;
        
        System.out.println("Enter the showtimeID you would like to view/update/remove: ");
        String selectedShowtimeID = sc.next();
        
        do {
            System.out.println(	"==================== SHOWTIME STAFF APP ====================\n" +
            					"| 1. View ALL Details						                |\n" +
			            		"| 2. Update   						                        |\n" +
			                    "| 3. Remove 						                        |\n" +
			                    "| 0. Back to MovieManager                                  |\n" +
			                    "===========================================================");
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    this.viewShowtime(selectedShowtimeID);
                    break;
                case 2:
                	this.updateShowtime(selectedShowtimeID);
                    break;
                case 3:
                	this.deleteShowtime(selectedShowtimeID);
                    break;
                case 0:
                	System.out.println("Back to ShowtimesList......");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose between 0-3.");
                    break;
            }
        } while (choice != 0);
    }
    
    
    private void viewShowtime(String selectedShowtimeID) {
    	int i;
    	for (Map.Entry showtimeElement : this.showtimes.entrySet()) {
    	    Showtime selectedShowtime = (Showtime) showtimeElement.getValue();
    		if (selectedShowtime.getShowtimeID().equalsIgnoreCase(selectedShowtimeID)) {
    			System.out.println("Here are all the details:");
    			System.out.println("ShowtimeID = " + selectedShowtime.getShowtimeID());
    			System.out.println(selectedShowtime.getCineplexName() + ", Cinema " + selectedShowtime.getCinema().getCinemaID() + ", Hall No. " + selectedShowtime.getCinema().getHallNo());
    			System.out.println("Movie Format: " + selectedShowtime.getMovieFormat());
    			System.out.println("Date/Time: " + selectedShowtime.getDateTime());
    			System.out.println("Cinema Status: " + selectedShowtime.getCinemaStatus());
    			System.out.println("Total Seats: " + selectedShowtime.getCinema().getTotalSeatNo() + ", Occupied Seats: " + selectedShowtime.getCinema().getOccupiedSeatsNo());
    			System.out.println("Cinema Type: " + selectedShowtime.getCinema().getCinemaType());
    			System.out.println("Cinema Layout: ");
    			selectedShowtime.getCinema().printCinemaLayout(); 			
    		}
    	}
    }
    
    private void updateShowtime(String showtimeID) {
        int choice;
        Showtime showtimeToUpdate = this.showtimes.get(showtimeID);
        if (showtimeToUpdate != null) {
            do {
                System.out.println(	"================= UPDATE SHOWTIME STAFF APP ================\n" +
                                    "| 1. Showtime Date Time      				                |\n" +
                                    "| 2. Movie ID                                              |\n" +
                                    "| 3. Cinema 						                        |\n" +
                                    "| 4. Cineplex Name 				                        |\n" +
                                    "| 5. Cinema Status 				                        |\n" +
                                    "| 6. Movie Format					                        |\n" +
                                    "| 0. Back to Showtime Staff App                            |\n" +
                                    "===========================================================");
                System.out.println("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        String newDateTime = sc.next();
                        showtimeToUpdate.setDateTime(this.dateTimeParser(newDateTime));
                        break;
                    case 2:
                        String newMovieID = sc.next();
                        showtimeToUpdate.setMovieID(newMovieID);
                        break;
                    case 3:
                        String newCinemaID = sc.next();
                        Cinema newCinema = CompanyManager.getInstance().getNewCinema(newCinemaID);
                        showtimeToUpdate.setCinema(newCinema);
                    case 4:
                        String cineplexName = sc.next();
                        showtimeToUpdate.setCineplexName(cineplexName);
                        break;
                    case 5:
                        showtimeToUpdate.setCinemaStatus(CinemaStatus.valueOf(sc.next()));
                        break;
                    case 6:
                        showtimeToUpdate.setMovieFormat(MovieFormat.valueOf(sc.next()));
                        break;
                    default:
                        System.out.println("Please enter an option 0 - 6!");
                        break;
                }
            } while (choice != 0);
            this.showtimes.put(showtimeID, showtimeToUpdate); // update hashmap entry
            this.save(showtimeToUpdate, showtimeID); // serialize file
        }
        else
        {
            System.out.println("Showtime not found!");
        }
    }

    private void createShowtime() {
        String showtimeID = IDHelper.getLatestID("showtime");
        LocalDateTime showtimeDateTime;
        String movieID;
        String cinemaID;
        String cineplexName;
        CinemaStatus cinemaStatus;
        MovieFormat movieFormat;

        System.out.println("Enter Showtime DateTime: ");
        showtimeDateTime = this.dateTimeParser(sc.next());
        System.out.println("Enter movieID: ");
        movieID = sc.next();
        System.out.println("Enter cinemaID: ");
        cinemaID = sc.next();
        Cinema cinema = CompanyManager.getInstance().getNewCinema(cinemaID);
        System.out.println("Enter Cineplex Name: ");
        cineplexName = sc.next();
        System.out.println("Enter cinema status: ");
        cinemaStatus = CinemaStatus.valueOf(sc.next());
        System.out.println("Enter movie format: ");
        movieFormat = MovieFormat.valueOf(sc.next());

        Showtime showtime = new Showtime();
        showtime.setShowtimeID(showtimeID);
        showtime.setMovieID(movieID);
        showtime.setDateTime(showtimeDateTime);
        showtime.setCinema(cinema);
        showtime.setCineplexName(cineplexName);
        showtime.setCinemaStatus(cinemaStatus);
        showtime.setMovieFormat(movieFormat);

        this.showtimes.put(showtimeID, showtime); // add new entry to hashmap
        this.save(showtime, showtimeID); // serialize file
        System.out.println("New showtime created!");
        System.out.println("Going back to showtime staff app ...");


    }

    public void deleteShowtime(String showtimeID) {
        Showtime foundShowtime = this.showtimes.get(showtimeID);
        if (foundShowtime != null) {
            foundShowtime.setCinemaStatus(CinemaStatus.SOLD_OUT);
            System.out.println("Showtime has been sold out, removed from view!");
            this.save(foundShowtime, showtimeID); // update .dat file
        }
        else {
            System.out.println("Showtime does not exist!");
        }
    }

    /***
     * Helper function to parse string to datetime
     * @param dateTimeString string to be parsed
     * @return DateTime object
     */
    private LocalDateTime dateTimeParser(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return dateTime;
    }
    
	// Private Serialization and Deserialization

    private Map <String,Showtime> load() {
        Map <String,Showtime> loadedShowtimes = new HashMap<>();
        File folder = new File("/data/showtimes");

        File[] listOfFiles = folder.listFiles();
        if(listOfFiles.length==0){
            return null;
        }
        for (File listOfFile : listOfFiles) {
            loadedShowtimes.keySet().add(listOfFile.getName().split("\\.(?=[^\\.]+$)")[0].split("_")[1]);
            String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/showtimes/" + listOfFile.getName().split("_")[0] + ".dat";
            loadedShowtimes.values().add((Showtime) SerializerHelper.deSerializeObject(filepath));
        }

        return loadedShowtimes;
    }

    public void save(Object objectToSave, String showtimeID) {
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/showtimes/" + showtimeID+ ".dat";
        SerializerHelper.serializeObject(objectToSave, filepath);
    }
}
