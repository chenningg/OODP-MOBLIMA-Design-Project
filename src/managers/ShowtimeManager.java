package managers;

import utils.SerializerHelper;
import utils.ProjectRootPathFinder;
import utils.IDHelper;
import movie_entities.Showtime;
import movie_entities.Movie;
import movie_entities.ShowingStatus;
import movie_entities.MovieFormat;
import company_entities.Cineplex;
import company_entities.Cinema;
import company_entities.CinemaStatus;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ShowtimeManager {
	// Attributes
    // HashMap of showtimeID to showtime object
	private Map <String, Showtime> showtimes = new HashMap<String, Showtime>();

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
        List<String> relevantShowtimeIDs = MovieManager.getInstance().getMoviebyID(movieID).getShowtimeIDs();
        List<Showtime> relevantShowtimes = new ArrayList<Showtime>();
        for (String showtimeID : relevantShowtimeIDs) {
            Showtime showtime = this.showtimes.get(showtimeID);
            relevantShowtimes.add(showtime);
        }

        int choice; // required to initialise
        // displays showtimes to users

        do {
            System.out.println("These is the list of relevant showtimes: ");
            int j;
            if (relevantShowtimes.size() == 0) {
                System.out.println("No showtimes found");
            }
            else {
                for (j = 0; j < relevantShowtimes.size(); j++) {
                    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd MMM yyyy, hh.mma");
                    System.out.println((j + 1) + ". " + relevantShowtimes.get(j).getCineplexName() + ", Cinema " + relevantShowtimes.get(j).getCinema().getCinemaID() + ", Hall No. " + relevantShowtimes.get(j).getCinema().getHallNo());
                    System.out.println("  " + "Movie Format: " + relevantShowtimes.get(j).getMovieFormat());
                    System.out.printf("  Date/Time: %s\n", relevantShowtimes.get(j).getDateTime().format(formatter));
                    System.out.println("");
                }
            }

            if (appType.equalsIgnoreCase("Staff") && relevantShowtimes.size()!=0) {
                System.out.println("==================== SHOWTIMES  =====================\n" +
                                    " 1. View/Update/Remove Specific Showtime          \n" +
                                    " 2. Create New Showtime                           \n" +
                                    " 0. Back to Showtimes List                        ");
                System.out.println("Enter choice:");
                while(!sc.hasNextInt()) {
                    System.out.println("Please enter a number!");
                    sc.next();
                }
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter choice of showtime: ");
                        while(!sc.hasNextInt()) {
                            System.out.println("Please enter a number!");
                            sc.next();
                        }
                        int option = sc.nextInt() - 1;
                        while (option >= relevantShowtimeIDs.size() || option < 0) {
                            System.out.println("Please enter a positive number up to " + relevantShowtimeIDs.size() + "!");
                            option = sc.nextInt() - 1;
                        }
                        String showtimeID = relevantShowtimeIDs.get(option);
                        this.showtimeMenuStaff(showtimeID);
                        break;
                    case 2:
                        Showtime showtime = this.createShowtime(movieID);
                        relevantShowtimes.add(showtime);
                        break;
                    case 0:
                        System.out.println("Back to Showtimes List......");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose between 0-1");
                        break;
                }
            }
            else if (appType.equalsIgnoreCase("Staff") && relevantShowtimes.size() == 0) {
                System.out.println("==================== SHOWTIMES  =====================\n" +
                        " 1. Create New Showtime                           \n" +
                        " 0. Back to Showtimes List                        ");
                System.out.println("Enter choice:");
                while(!sc.hasNextInt()) {
                    System.out.println("Please enter a number!");
                    sc.next();
                }
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        Showtime showtime = this.createShowtime(movieID);
                        relevantShowtimes.add(showtime);
                        break;
                    case 0:
                        System.out.println("Back to Showtimes List......");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose between 0-1");
                        break;
                }
            }
            else if (appType.equalsIgnoreCase("Customer")) {
                Movie movie = MovieManager.getInstance().getMoviebyID(movieID);
                if (movie.getShowingStatus().equals(ShowingStatus.COMING_SOON)) {
                	choice= 0;
                }
                else {
                    System.out.println("==================== SHOWTIMES  ====================\n" +
                            " 1. View Specific Showtime (Details / Booking)    \n" +
                            " 0. Back to MovieManager                          ");
                System.out.println("Enter choice:");
                while(!sc.hasNextInt()) {
                    System.out.println("Please enter a number!");
                    sc.next();
                }
                choice = sc.nextInt();
                }
                switch (choice) {
                    case 1:
                        System.out.println("Enter choice of showtime: ");
                        while(!sc.hasNextInt()) {
                            System.out.println("Please enter a number!");
                            sc.next();
                        }
                        int option = sc.nextInt() - 1;
                        while (option >= relevantShowtimeIDs.size() || option < 0) {
                            System.out.println("Please enter a positive number up to " + relevantShowtimeIDs.size() + "!");
                            option = sc.nextInt() - 1;
                        }
                        String showtimeID = relevantShowtimeIDs.get(option);
                        this.showtimeMenuCustomer(showtimeID);
                        break;
                    case 0:
                        System.out.println("Back to MovieManager......");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose between 0-1");
                        break;
                }
            }
            else {
                choice = 0;
            }
        } while (choice != 0);
    }

    private void showtimeMenuCustomer(String selectedShowtimeID) {
        int choice;

        String movieID = this.showtimes.get(selectedShowtimeID).getMovieID();
        Movie movie = MovieManager.getInstance().getMoviebyID(movieID);
        if (movie.getShowingStatus().equals(ShowingStatus.COMING_SOON)) {}
        else {
            do {
                System.out.println("================== SHOWTIME CUSTOMER APP ===================\n" +
                        " 1. View ALL Details                                      \n" +
                        " 2. Book Showtime                                         \n" +
                        " 0. Back to MovieManager                                  ");
                System.out.println("Enter choice: ");
                while(!sc.hasNextInt()) {
                    System.out.println("Please enter a number!");
                    sc.next();
                }
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        this.viewShowtime(selectedShowtimeID);
                        break;
                    case 2:
                        Showtime showtime = this.findShowtime(selectedShowtimeID);
                        BookingManager.getInstance().startSeatSelection(showtime);
                        break;
                    case 0:
                        System.out.println("Back to Showtimes List......");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose between 0-3.");
                        break;
                }
            } while (choice != 0);
        }
    }

    private void showtimeMenuStaff(String showtimeID) {
        int choice;

        do {
            System.out.println(	"==================== SHOWTIME STAFF APP ====================\n" +
            					" 1. View ALL Details                                      \n" +
			            		" 2. Update                                                \n" +
			                    " 3. Remove                                                \n" +
			                    " 0. Back to MovieManager                                  ");
            System.out.println("Enter choice: ");
            while(!sc.hasNextInt()) {
                System.out.println("Please enter a number!");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    this.viewShowtime(showtimeID);
                    break;
                case 2:
                	this.updateShowtime(showtimeID);
                    break;
                case 3:
                	this.deleteShowtime(showtimeID);
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
        Showtime selectedShowtime = this.showtimes.get(selectedShowtimeID);
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd MMM yyyy, hh.mma");
        System.out.println("Here are all the details:");
        System.out.println("ShowtimeID = " + selectedShowtime.getShowtimeID());
        System.out.println(selectedShowtime.getCineplexName() + ", Cinema " + selectedShowtime.getCinema().getCinemaID() + ", Hall No. " + selectedShowtime.getCinema().getHallNo());
        System.out.println("Movie Format: " + selectedShowtime.getMovieFormat());
        System.out.printf("Date/Time: %s\n", selectedShowtime.getDateTime().format(formatter));
        System.out.println("Cinema Status: " + selectedShowtime.getCinemaStatus());
        System.out.println("Total Seats: " + selectedShowtime.getCinema().getTotalSeatNo() + ", Occupied Seats: " + selectedShowtime.getCinema().getOccupiedSeatsNo());
        System.out.println("Cinema Type: " + selectedShowtime.getCinema().getCinemaType());
        System.out.println("Cinema Layout: ");
        selectedShowtime.getCinema().printCinemaLayout();
    }

    private void updateShowtime(String showtimeID) {
        int choice;
        Showtime showtimeToUpdate = this.showtimes.get(showtimeID);
        if (showtimeToUpdate != null) {
            do {
                System.out.println(	"================= UPDATE SHOWTIME STAFF APP ================\n" +
                                    " 1. Showtime Date Time                                    \n" +
                                    " 2. Movie ID                                              \n" +
                                    " 3. Cinema                                                \n" +
                                    " 4. Cineplex Name                                         \n" +
                                    " 5. Cinema Status                                         \n" +
                                    " 6. Movie Format                                          \n" +
                                    " 0. Back to Showtime Staff App                            ");

                System.out.println("Enter choice: ");
                while(!sc.hasNextInt()) {
                    System.out.println("Please enter a number!");
                    sc.next();
                }
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        sc.nextLine();
                        System.out.println("Enter new Showtime datetime (dd/MM/yyyy HH:mm): ");
                        String newDateTime = sc.nextLine();
                        LocalDateTime localDateTime = this.dateTimeParser(newDateTime);
                        while (localDateTime == null) {
                            System.out.println("Enter new Showtime datetime (dd/MM/yyyy HH:mm): ");
                            newDateTime = sc.nextLine();
                            localDateTime = this.dateTimeParser(newDateTime);
                        }
                        showtimeToUpdate.setDateTime(this.dateTimeParser(newDateTime));
                        break;
                    case 2:
                        System.out.println("Enter new movie ID: ");
                        String newMovieID = sc.next();
                        showtimeToUpdate.setMovieID(newMovieID);
                        break;
                    case 3:
                        System.out.println("Enter new cinema ID: ");
                        String newCinemaID = sc.next();
                        Cinema newCinema = CompanyManager.getInstance().getNewCinema(newCinemaID);
                        showtimeToUpdate.setCinema(newCinema);
                        break;
                    case 4:
                        System.out.println("Enter new cineplex name: ");
                        String cineplexName = sc.next();
                        showtimeToUpdate.setCineplexName(cineplexName);
                        break;
                    case 5:
                        System.out.println("Enter new cinema status: ");
                        String cinemaStatus = sc.next();
                        while (!cinemaStatusValidator(cinemaStatus)) {
                            System.out.println("Enter new movie format: ");
                            cinemaStatus = sc.next();
                        }
                        showtimeToUpdate.setCinemaStatus(CinemaStatus.valueOf(sc.next()));
                        break;
                    case 6:
                        System.out.println("Enter new movie format: ");
                        String movieFormat = sc.next();
                        while (!movieFormatValidator(movieFormat)) {
                            System.out.println("Enter new movie format: ");
                            movieFormat = sc.next();
                        }
                        showtimeToUpdate.setMovieFormat(MovieFormat.valueOf(sc.next()));
                        break;
                    case 0:
                        System.out.println("Going back to Showtime Staff App ...");
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

    private Showtime createShowtime(String movieID) {
        String showtimeID = IDHelper.getLatestID("showtime");
        String cinemaID;
        String cineplexName;
        CinemaStatus cinemaStatus;
        MovieFormat movieFormat;

        sc.nextLine();
        System.out.println("Enter showtime datetime (dd/MM/yyyy HH:mm): ");
        String newDateTime = sc.nextLine();
        LocalDateTime localDateTime = this.dateTimeParser(newDateTime);
        while (localDateTime == null) {
            System.out.println("Enter showtime datetime (dd/MM/yyyy HH:mm): ");
            newDateTime = sc.nextLine();
            localDateTime = this.dateTimeParser(newDateTime);
        }

        List<Cineplex> cineplexList = CompanyManager.getInstance().getCompany().getCineplexes();
        System.out.println("List of Cineplexes: ");
        for(int i=0;i<cineplexList.size();i++){
            System.out.println(i+1 + ". " + cineplexList.get(i).getCineplexName());
        }
        System.out.println("Choose a cineplex: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input type. Please enter an integer value.");
            sc.next(); // Remove newline character
        }
        int choice = sc.nextInt()-1;
        cineplexName =cineplexList.get(choice).getCineplexName();

        List<Cinema> cinemaList = cineplexList.get(choice).getCinemas();
        System.out.println("List of Cinemas: ");
        for(int i=0;i<cinemaList.size();i++){
            System.out.println(i+1 + ". " + cinemaList.get(i).getCinemaID());
        }

        System.out.println("Pick a cinema: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input type. Please enter an integer value.");
            sc.next(); // Remove newline character
        }
        int cinemaChoice = sc.nextInt()-1;
        cinemaID = cinemaList.get(cinemaChoice).getCinemaID();
        Cinema newCinema = CompanyManager.getInstance().getNewCinema(cinemaID);

        cinemaStatus = CinemaStatus.AVAILABLE;

        System.out.println("Available movie formats");
        List<MovieFormat> movieFormats = MovieManager.getInstance().getMoviebyID(movieID).getMovieFormats();
        for(int i=0;i<movieFormats.size();i++){
            System.out.println(i+1 + ". "+movieFormats.get(i).toString());
        }
        System.out.println("Pick format: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input type. Please enter an integer value.");
            sc.next(); // Remove newline character
        }
        int format = sc.nextInt()-1;
        movieFormat = movieFormats.get(format);

        Showtime showtime = new Showtime();
        showtime.setShowtimeID(showtimeID);
        showtime.setMovieID(movieID);
        showtime.setDateTime(localDateTime);
        showtime.setCinema(newCinema);
        showtime.setCineplexName(cineplexName);
        showtime.setCinemaStatus(cinemaStatus);
        showtime.setMovieFormat(movieFormat);

        MovieManager.getInstance().updateShowtimes(movieID, showtimeID);
        this.showtimes.put(showtimeID, showtime); // add new entry to hashmap
        this.save(showtime, showtimeID); // serialize file
        System.out.println("New showtime created!");
        System.out.println("Going back to showtime staff app ...");
        return showtime;
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

    public Showtime findShowtime(String showtimeID) {
        Showtime showtime = (Showtime) this.showtimes.get(showtimeID);
        return showtime;
    }

    /***
     * Helper function to parse string to datetime
     * @param dateTimeString string to be parsed
     * @return DateTime object
     */
    private LocalDateTime dateTimeParser(String dateTimeString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
            return dateTime;
        }
        catch (DateTimeParseException dtpe) {
            System.out.println("Wrong date time format entered!");
            return null;
        }
    }

    private boolean cinemaStatusValidator(String cinemaStatus) {
        try {
            CinemaStatus cinemaStatusEnum = CinemaStatus.valueOf(cinemaStatus);
            return true;
        }
        catch (IllegalArgumentException ex) {
            System.out.println("Wrong cinema status entered!");
            return false;
        }
    }

    private boolean movieFormatValidator(String movieFormat) {
        try {
            MovieFormat movieFormatEnum = MovieFormat.valueOf(movieFormat);
            return true;
        }
        catch (IllegalArgumentException ex) {
            System.out.println("Wrong movie format entered!");
            return false;
        }
    }

	// Private Serialization and Deserialization

    private Map <String, Showtime> load() {
        Map <String, Showtime> loadedShowtimes = new HashMap<String, Showtime>();
        File folder = new File(ProjectRootPathFinder.findProjectRootPath() + "/data/showtimes");

        File[] listOfFiles = folder.listFiles();
        if(listOfFiles != null){
	        for (File listOfFile : listOfFiles) {
                String filepath = listOfFile.getPath();
                Showtime newShowtime = (Showtime) SerializerHelper.deSerializeObject(filepath);
	            String fileID = listOfFile.getName().split("\\.(?=[^\\.]+$)")[0].split("_")[1];
	            loadedShowtimes.put(fileID, newShowtime);
	        }
        }

        return loadedShowtimes;
    }

    public void save(Object objectToSave, String showtimeID) {
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/showtimes/showtime_" + showtimeID + ".dat";
        SerializerHelper.serializeObject(objectToSave, filepath);
    }
}