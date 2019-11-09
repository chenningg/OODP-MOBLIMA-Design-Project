import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class ShowtimeManager {
    private ArrayList<Showtime> showtimes;

    // Singleton things
    private static ShowtimeManager single_instance = null;

    private ShowtimeManager() {
        this.showtimes = loadObject();
    }

    public static ShowtimeManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ShowtimeManager();
        return single_instance;
    }

    Scanner sc = new Scanner(System.in);


    // Methods
    // Showtime manager called at this point with movie
    public void displayMovieShowtimes(Movie movie, Cineplex cineplex)
    {
        HashMap <Integer, Showtime> showtimeSelect = new HashMap<>();
        BookingManager bookingManager = BookingManager.getInstance();
        if (cineplex == null)
        {
            System.out.println("Enter cineplex ID: ");
            String cineplexID = sc.next();
            cineplex = new Cineplex(cineplexID);
        }
        System.out.println("Please select showtime: ");
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
        bookingManager.startSeatSelection(selectedShowtime);
        if (!showtimeSelect.containsKey(choice)) {
            // TODO: call back to previous view
        }
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
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        String dateInString = inputLine;
                        LocalDateTime dateTime = this.dateTimeParser(dateInString);
                        newShowtime.setDateTime(dateTime);
                        System.out.println("Showtime read and added!");
                        break;
                    case 1:
                        // second line of file is the movieID
                        String movieID = inputLine;
                        Movie foundMovie = this.findMovie(movieID);
                        if (foundMovie == null) {
                            System.out.println("Movie not found!");
                        }
                        else {
                            newShowtime.setMovie(foundMovie);
                        }
                        break;
                    case 2:
                        // third line of file is the cinemaID, which is used to construct Cinema Object
                        String cinemaID = inputLine;
                        Cinema cinema = new Cinema(cinemaID);
                        newShowtime.setCinema(cinema); // TODO: does this set seat layout already?
                        break;
                    case 3:
                        // fourth line of file is the cineplexID
                        String cineplexID = inputLine;
                        Cineplex cineplex = new Cineplex(cineplexID);
                        newShowtime.setCineplex(cineplex);
                        break;
                    case 4:
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
        String showtimeID;
        LocalDateTime showtimeDateTime;
        String movieID;
        Movie movie;
        String cinemaID;
        Cinema cinema;
        String cineplexID;
        Cineplex cineplex;
        CinemaStatus cinemaStatus;
        do {
            System.out.println("1. Enter showtimeID");
            System.out.println("2. Enter showtime DateTime");
            System.out.println("3. Enter movieID");
            System.out.println("4. Enter cinemaID");
            System.out.println("5. Enter cineplexID");
            System.out.println("6. Enter cinema status");
            System.out.println("7. Confirm entry");
            System.out.println("8. Back"); // TODO: Implement this
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
                    if (movie == null || showtimeID == null || showtimeDateTime == null || movieID == null || cinemaID == null || cineplexID == null || cinemaStatus == null) {
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
                        break;
                    }
                case 8:
                    // TODO: add call to previous view
                    break;
            }
        } while (choice < 8);
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
                System.out.println("7. Back"); // TODO: is there a exit flow? and flow to where?
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
                        // TODO: exit view flow
                        break;
                }
            } while (choice < 8);
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
    public ArrayList<Showtime> loadObject() {
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/showtimes/showtimes.dat";
        System.out.println("Showtimes loaded!");
        return (ArrayList<Showtime>) SerializerHelper.deSerializeObject(filepath);
    }

    public void saveObject() {
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/showtimes/showtimes.dat";
        SerializerHelper.serializeObject(this.showtimes, filepath);
        System.out.println("Showtimes Saved!");
    }

    private Movie findMovie(String movieID) {
        MovieManager movieManager = MovieManager.getInstance();
        ArrayList<Movie> moviesInMovieManager = movieManager.getMovies();
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return dateTime;
    }
}
