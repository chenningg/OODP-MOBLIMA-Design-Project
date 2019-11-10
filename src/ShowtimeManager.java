import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ShowtimeManager {
    private List<Showtime> showtimes;

    // Singleton things
    private static ShowtimeManager single_instance = null;

    private ShowtimeManager() {
        List<Showtime> serializedObject = this.loadObject();
        if (serializedObject != null) {
            this.showtimes = serializedObject;
        } else {
            this.showtimes = new ArrayList<>();
        }
    }

    public static ShowtimeManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ShowtimeManager();
        return single_instance;
    }

    Scanner sc = new Scanner(System.in);


    // Methods

    public void displayMoviesfromCineplex(Cineplex cineplex)
    {
        MovieManager mm = MovieManager.getInstance();
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        for (Showtime showtime : showtimes)
        {
            if (showtime.getCineplex() == cineplex && !movieList.contains(showtime.getMovie())){
                movieList.add(showtime.getMovie());
            }
        }
        for(int i=0;i<movieList.size();i++){
            System.out.println(i+1 +". "+ movieList.get(i).getTitle());
        }
        System.out.println("Choose a movie:");
        int option = sc.nextInt();
        mm.submovieMenu(movieList.get(option-1));
    }

    // Showtime manager called at this point with movie
    public void displayMovieShowtimes(Movie movie, Cineplex cineplex)
    {
        Map <Integer, Showtime> showtimeSelect = new HashMap<>();
        BookingManager bookingManager = BookingManager.getInstance();
        if (cineplex == null)
        {
            System.out.println("Enter cineplex ID: ");
            String cineplexID = sc.next();
            cineplex = new Cineplex(cineplexID);
        }
        System.out.println("Please select showtime: ");
        System.out.println("0. Back");
        int count = 1;
        for (Showtime showtime : showtimes)
        {
            if (showtime.getMovie() == movie) {
                System.out.println(count + ". " + movie.getTitle() + " is available at " + showtime.getDateTime());
                showtimeSelect.put(count, showtime);
                count++;
            }
        }
        System.out.println(count + ". Exit");
        int choice = sc.nextInt();
        // booking manager call here, to book showtime mapped by hashmap
        Showtime selectedShowtime = showtimeSelect.get(choice);
        if (choice == 0) {
            System.out.println("Going back...");
        }
        else if (showtimeSelect.containsKey(choice)) {
            bookingManager.startSeatSelection(selectedShowtime);
        }
        else {
            System.out.println("Showtime selected not available!");
        }
        // end of function control, goes back!
    }

    public void readShowtime(String showtimeID) {
        try {
            Showtime newShowtime = new Showtime();
            // Get filepath
            String filePath = ProjectRootPathFinder.findProjectRootPath();
            if (filePath == null) {
                throw new IOException("Cannot find root");
            } else {
                // read active showtimes
                filePath = filePath + "/data/showtimes/active" + showtimeID + ".txt";
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
                    case 0:
                        // first line of file is DateTime of showtime
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        String dateInString = inputLine;
                        LocalDateTime dateTime = this.dateTimeParser(dateInString);
                        newShowtime.setDateTime(dateTime);
                        System.out.println("Showtime read and added!");
                        break;
                    // second line of file is showtimeID
                    case 1:
                        newShowtime.setShowtimeID(showtimeID);
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
                }
                i++;
            } while (inputLine != null);
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
                        break;
                    }
                default:
                    System.out.println("Please enter an option 0 - 8!");
                    break;
            }
        } while (choice != 0);
    }

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
        }
        else
        {
            System.out.println("Showtime not found!");
        }
    }

    public void deleteShowtime(String showtimeID) {
        for (Showtime showtime : showtimes) {
            String storedShowtimeID = showtime.getShowtimeID();
            if (storedShowtimeID.equalsIgnoreCase(showtimeID)) {
                showtimes.remove(showtime);
                System.out.println("Showtime deleted!");
                break;
            }
        }
    }
    public List<Showtime> loadObject() {
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/showtimes/showtimes.dat";
        System.out.println("Showtimes loaded!");
        return (List<Showtime>) SerializerHelper.deSerializeObject(filepath);
    }

    public void saveObject() {
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/showtimes/showtimes.dat";
        SerializerHelper.serializeObject(this.showtimes, filepath);
        System.out.println("Showtimes Saved!");
    }

    private Movie findMovie(String movieID) {
        MovieManager movieManager = MovieManager.getInstance();
        List<Movie> moviesInMovieManager = movieManager.getMovies();
        for (Movie movie : moviesInMovieManager)
        {
            String movieTitle = movie.getMovieID();
            if (movieTitle.equalsIgnoreCase(movieID))
            {
                return movie;
            }
        }
        return null; // if movie not found
    }

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
}
