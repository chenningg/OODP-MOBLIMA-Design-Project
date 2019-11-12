import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class ShowtimeManager {
	// Attributes
	private List<Showtime> showtimes;
    private Scanner sc = new Scanner(System.in);
    private static ShowtimeManager single_instance = null;

    public static ShowtimeManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ShowtimeManager();
        return single_instance;
    }
    private ShowtimeManager() {
        List<Showtime> serializedObject = this.load();
        if (serializedObject != null) {
            this.showtimes = serializedObject;
        } else {
            this.showtimes = new ArrayList<>();
            this.save();
        }
    }




	////////////////////////////////////////////////////////////////////////////////////////////
    
    // Still editing
    // Must pass control over to other managers
    // Do not keep the checking within here, only keep the logic
    // How should we handle scanners? Pass it on and on or create in each class
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    // Public exposed methods to app
    public void showtimeMenuStaff(Scanner sc) {
        int choice;
        
        do {
            System.out.println("==================== SHOWTIME STAFF APP ====================\n" +
                    "| 1. View showtime for a movie (enter movieID)              \n" +
                    "| 2. Read From File                                        |\n" +
                    "| 3. Create Showtime Entry                                 |\n" +
                    "| 4. Update Showtime Entry                                 |\n" +
                    "| 5. Delete Showtime Entry                                 |\n" +
                    "| 0. Back                                                  |\n" +
                    "===========================================================");
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter here: ");
                    this.viewShowtimes(sc.next());
                    break;
                case 2:
                    System.out.println("Enter showtimeID to be read from file: ");
                    this.readShowtime(sc.next());
                    break;
                case 3:
                    this.createShowtime();
                    break;
                case 4:
                    System.out.println("Enter showtimeID to be updated: ");
                    this.updateShowtime(sc.next());
                    break;
                case 5:
                    System.out.println("Enter showtimeID to be deleted: ");
                    this.deleteShowtime(sc.next());
                    break;
                case 0:
                	System.out.println("Back to StaffApp......");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose between 0-5.");
                    break;
            }
        } while (choice != 0);
    }
    
    
    
    
    
    // Private methods
    

    /***
     * Allows user to view showtimes of a movie
     * @param movieID MovieID entered to search for the movie
     */
    private void viewShowtimes(String movieID) {
        Movie movie = this.findMovie(movieID);
        if (movie != null) {
            int count = 1;
            for (Showtime showtime : showtimes) {
                if (showtime.getMovie().getMovieID().equalsIgnoreCase(movie.getMovieID())) {
                    System.out.println("Showtime " + count + ": ");
                    System.out.println("Showing at Cineplex: " + showtime.getCineplex().getCineplexName() + " Hall: " + showtime.getCinema().getHallNo());
                    System.out.println("Show timing: " + showtime.getDateTime().toString());
                    System.out.println("Cinema status: " + showtime.getCinemaStatus().toString());
                    System.out.println("Movie format: " + showtime.getMovieFormat().toString());
                    count++;
                }
            }
        }
        else {
            System.out.println("Movie does not exist!");
        }
    }

    /***
     * Lists out movies at the Cineplex to customers.
     * @param cineplexID CineplexID entered is the one to be searched.
     */
    public void displayMoviesfromCineplex(String cineplexID)
    {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        for (Showtime showtime : showtimes)
        {
            if (showtime.getCineplex().getCineplexID().equalsIgnoreCase(cineplexID) && !movieList.contains(showtime.getMovie())){
                movieList.add(showtime.getMovie());
            }
        }
        for(int i=0;i<movieList.size();i++){
            System.out.println(i+1 +". "+ movieList.get(i).getTitle());
        }
        System.out.println("Choose a movie:");
        int option = sc.nextInt();
        MovieManager.getInstance().submovieMenu(movieList.get(option-1));
    }

    /***
     * Searches for showtimes by movie and cineplex
     * @param movie Movie to be searched
     * @param cineplex Cineplex, if entered, is searched for the showtime. Else,
     *                 Cineplex is entered by the user to proceed with search.
     */
    // Showtime manager called at this point with movie
    public void displayMovieShowtimes(Movie movie, Cineplex cineplex)
    {
        Map <Integer, Showtime> showtimeSelect = new HashMap<>();
        if (cineplex == null)
        {
            System.out.println("Enter cineplexID: ");
            String cineplexID = sc.next();
            cineplex = new Cineplex(cineplexID);
        }
        System.out.println("Please select showtime: ");
        System.out.println("0. Back");
        int count = 1;
        for (Showtime showtime : showtimes)
        {
            if (showtime.getMovie().getMovieID().equalsIgnoreCase(movie.getMovieID())){
                System.out.println(count + ". " + movie.getTitle() + " is available at " + showtime.getDateTime().toString());
                showtimeSelect.put(count, showtime);
                count++;
            }
        }
        int choice = sc.nextInt();
        // booking manager call here, to book showtime mapped by hashmap
        Showtime selectedShowtime = showtimeSelect.get(choice);
        if (choice == 0) {
            System.out.println("Going back...");
        }
        else if (showtimeSelect.containsKey(choice)) {
        	BookingManager.getInstance().startSeatSelection(selectedShowtime);
        }
        else {
            System.out.println("Showtime selected not available!");
        }
        // end of function control, goes back!
    }

    /***
     * To read showtime data from text file
     * @param showtimeID showtime identifier
     */
    public void readShowtime(String showtimeID) {
        try {
            Showtime newShowtime = new Showtime();
            // Get filepath
            String filePath = ProjectRootPathFinder.findProjectRootPath();
            if (filePath == null) {
                throw new IOException("Cannot find root");
            } else {
                // read active showtimes
                filePath = filePath + "/data/initialisation/showtimes/" + showtimeID + ".txt";
            }

            // Open file and traverse it
            FileReader frStream = new FileReader( filePath );
            BufferedReader brStream = new BufferedReader( frStream );
            String inputLine;
            int i = 0;

            do {
                inputLine = brStream.readLine(); // read in a line
                if (inputLine == null) {break;} // end of file

                switch (i) {
                    // first line of file is showtimeID
                    case 0:
                        newShowtime.setShowtimeID(showtimeID);
                        break;
                    case 1:
                        // first line of file is DateTime of showtime
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        String dateInString = inputLine;
                        LocalDateTime dateTime = this.dateTimeParser(dateInString);
                        newShowtime.setDateTime(dateTime);
                        System.out.println("Showtime read and added!");
                        break;
                    case 2:
                        // third line of file is the movieID
                        String movieID = inputLine;
                        Movie foundMovie = this.findMovie(movieID);
                        if (foundMovie == null) {
                            System.out.println("Movie not found!");
                        }
                        else {
                            newShowtime.setMovie(foundMovie);
                        }
                        break;
                    // fourth line is movieformat
                    case 3:
                        newShowtime.setMovieFormat(MovieFormat.valueOf(inputLine));
                        break;
                    case 4:
                        // fifth line of file is the cinemaID, which is used to construct Cinema Object
                        String cinemaID = inputLine;
                        Cinema cinema = new Cinema(cinemaID);
                        newShowtime.setCinema(cinema); // TODO: does this set seat layout already?
                        break;
                    case 5:
                        // sixth line of file is the cineplexID
                        String cineplexID = inputLine;
                        Cineplex cineplex = new Cineplex(cineplexID);
                        newShowtime.setCineplex(cineplex);
                        break;
                    case 6:
                        // fifth line will be cinema status
                        String cinemaStatus = inputLine;
                        CinemaStatus cinemaStatus1 = CinemaStatus.valueOf(cinemaStatus);
                        newShowtime.setCinemaStatus(cinemaStatus1);
                        break;
                    }
                i++;
            } while (inputLine != null);
            this.showtimes.add(newShowtime);
            this.save(); // save whole array
        }
        catch ( FileNotFoundException e ) {
        System.out.println( "Error opening the input file!" + e.getMessage() );
        System.exit( 0 );
        }
        catch ( IOException e ) {
        System.out.println( "IO Error!" + e.getMessage() );
        e.printStackTrace();
        System.exit( 0 );
        }
    }

    /***
     * For staff to create a showtime from console
     */
    public void createShowtime() {
        int choice;
        String showtimeID=null;
        LocalDateTime showtimeDateTime = null;
        String movieID=null;
        Movie movie=null;
        String cinemaID=null;
        MovieFormat movieFormat=null;
        Cinema cinema=null;
        String cineplexID=null;
        Cineplex cineplex=null;
        CinemaStatus cinemaStatus=null;
        do {
            System.out.println("1. Enter showtimeID");
            System.out.println("2. Enter showtime DateTime");
            System.out.println("3. Enter movieID");
            System.out.println("4. Enter cinemaID");
            System.out.println("5. Enter cineplexID");
            System.out.println("6. Enter cinema status");
            System.out.println("7. Enter movie format");
            System.out.println("8. Confirm entry");
            System.out.println("0. Back");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter here: ");
                    showtimeID = sc.next();
                    break;
                case 2:
                    System.out.println("Enter here: ");
                    String DateTime = sc.next();
                    showtimeDateTime = this.dateTimeParser(DateTime);
                    break;
                case 3:
                    System.out.println("Enter here: ");
                    movieID = sc.next();
                    movie = this.findMovie(movieID);
                    break;
                case 4:
                    System.out.println("Enter here: ");
                    cinemaID = sc.next();
                    cinema = new Cinema(cinemaID);
                    break;
                case 5:
                    System.out.println("Enter here: ");
                    cineplexID = sc.next();
                    cineplex = new Cineplex(cineplexID);
                    break;
                case 6:
                    System.out.println("Enter here: ");
                    cinemaStatus = CinemaStatus.valueOf(sc.next());
                    break;
                case 7:
                    System.out.println("Enter here: ");
                    movieFormat = MovieFormat.valueOf(sc.next());
                    break;
                case 8:
                    if (movieFormat == null || movie == null || showtimeID == null || showtimeDateTime == null || movieID == null || cinemaID == null || cineplexID == null || cinemaStatus == null) {
                        System.out.println("All fields must be entered!");
                        break;
                    }
                    else {
                        Showtime showtime = new Showtime();
                        showtime.setShowtimeID(showtimeID);
                        showtime.setMovie(movie);
                        showtime.setDateTime(showtimeDateTime);
                        showtime.setCinema(cinema);
                        showtime.setCineplex(cineplex);
                        showtime.setCinemaStatus(cinemaStatus);
                        showtime.setMovieFormat(movieFormat);
                        this.showtimes.add(showtime);
                        this.save(); // save whole array
                        break;
                    }
                default:
                    System.out.println("Please enter an option 0 - 8!");
                    break;
            }
        } while (choice != 0);
    }

    /***
     * Update an existing showtime
     * @param showtimeID identifier entered to find existing showtime
     */
    public void updateShowtime(String showtimeID) {
        int choice;
        Showtime foundShowtime = this.findShowtime(showtimeID);
        if (foundShowtime != null) {
            do {
                System.out.println("1. Update showtimeID");
                System.out.println("2. Update showtime DateTime");
                System.out.println("3. Update movie (enter movieID");
                System.out.println("4. Update cinema (enter cinemaID)");
                System.out.println("5. Update cineplex (enter cineplexID)");
                System.out.println("6. Update cinema status");
                System.out.println("7. Update movie format");
                System.out.println("0. Back");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter here: ");
                        String newShowtimeID = sc.next();
                        foundShowtime.setShowtimeID(showtimeID);
                        break;
                    case 2:
                        System.out.println("Enter here: ");
                        String newDateTime = sc.next();
                        foundShowtime.setDateTime(this.dateTimeParser(newDateTime));
                        break;
                    case 3:
                        System.out.println("Enter here: ");
                        String newMovieID = sc.next();
                        Movie newMovie = this.findMovie(newMovieID);
                        foundShowtime.setMovie(newMovie);
                        break;
                    case 4:
                        System.out.println("Enter here: ");
                        String newCinemaID = sc.next();
                        // call new Cinema here since layout will be refreshed
                        Cinema newCinema = new Cinema(newCinemaID);
                        foundShowtime.setCinema(newCinema);
                        break;
                    case 5:
                        System.out.println("Enter here: ");
                        String newCineplexID = sc.next();
                        // call new Cineplex here since halls may be refreshed
                        Cineplex newCineplex = new Cineplex(newCineplexID);
                        foundShowtime.setCineplex(newCineplex);
                        break;
                    case 6:
                        System.out.println("Enter here: ");
                        foundShowtime.setCinemaStatus(CinemaStatus.valueOf(sc.next()));
                        break;
                    case 7:
                        System.out.println("Enter here: ");
                        foundShowtime.setMovieFormat(MovieFormat.valueOf(sc.next()));
                        break;
                    default:
                        System.out.println("Please enter an option 0 - 7!");
                        break;
                }
            } while (choice != 0);
            this.save(); // save whole array
        }
        else
        {
            System.out.println("Showtime not found!");
        }
    }

    /***
     * For staff to delete a showtime
     * @param showtimeID for the indentification of the showtime
     */
    public void deleteShowtime(String showtimeID) {
        Showtime foundShowtime = this.findShowtime(showtimeID);
        if (foundShowtime != null) {
            showtimes.remove(foundShowtime);
            System.out.println("Showtime deleted!");
        }
        else {
            System.out.println("Showtime does not exist!");
        }
        this.save();
    }



    /***
     * Helper function to return movie object
     * @param movieID To convert a movieID to a Movie object
     * @return Movie object returned from search
     */
    private Movie findMovie(String movieID) {
        ArrayList<Movie> moviesInMovieManager = MovieManager.getInstance().getMovies();
        for (Movie movie : moviesInMovieManager)
        {
            String movieTitle = movie.getMovieID();
            if(movieTitle==null){
                return null;
            }
            if (movieTitle.equalsIgnoreCase(movieID))
            {
                return movie;
            }
        }
        return null; // if movie not found
    }

    /***
     * To find showtime object
     * @param showtimeID Convert ID to showtime object
     * @return showtime object
     */
    private Showtime findShowtime(String showtimeID) {
        for (Showtime showtime : showtimes) {
            String showtimeIDInManager = showtime.getShowtimeID();
            if (showtimeIDInManager.equalsIgnoreCase(showtimeID))
            {
                return showtime;
            }
        }
        return null;
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

    // Getters and Setters
    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(List<Showtime> showtimes) {
        this.showtimes = showtimes;
    }
    
    
    
	// Private Serialization and Deserialization
    /***
     * To reload the object via deserialization
     * @return returns the list of showtimes to the constructor
     */
    public List<Showtime> load() {
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/showtimes/showtimes.dat";
        return (List<Showtime>) SerializerHelper.deSerializeObject(filepath);
    }

    /***
     * Save the array list, for loading later
     */
    public void save() {
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/showtimes/showtimes.dat";
        SerializerHelper.serializeObject(this.showtimes, filepath);
    }
}
