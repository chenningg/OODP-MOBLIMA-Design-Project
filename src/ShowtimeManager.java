import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ShowtimeManager implements Serializable {
    private ArrayList<Showtime> showtimes;

    // Singleton things
    private static ShowtimeManager single_instance = null;

    private ShowtimeManager() {}

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
                System.out.println(count + ". " + movie.getTitle() + " is available at " + showtime.getDate() + showtime.getTime());
                showtimeSelect.put(count, showtime);
                count++;
            }
        }
        System.out.println(count + ". Exit");
        int choice = sc.nextInt();
        // TODO: booking manager call here, to book showtime mapped by hashmap
        Showtime selectedShowtime = showtimeSelect.get(choice);
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
                        LocalDateTime dateTime = LocalDateTime.parse(dateInString, formatter);
                        newShowtime.setDateTime(dateTime);
                        System.out.println("Showtime read and added!");
                        break;
                    case 1:
                        // second line of file is the Movie
                        String movieName = inputLine;
                        MovieManager movieManager = MovieManager.getInstance();
                        ArrayList<Movie> moviesInMovieManager = movieManager.getMovies();
                        for (Movie movie : moviesInMovieManager)
                        {
                            String movieNameRead = movie.getTitle();
                            if (movieNameRead.equalsIgnoreCase(movieName))
                            {
                                newShowtime.setMovie(movie);
                                System.out.println("Movie read and added!");
                                break;
                            }
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
        String movieTitle;
        String cinemaID;
        String cineplexID;
        CinemaStatus cinemaStatus;
        do {
            System.out.println("1. Enter showtimeID");
            System.out.println("2. Enter showtime DateTime");
            System.out.println("3. Enter movie title");
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
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    showtimeDateTime = LocalDateTime.parse(DateTime, formatter);
                    break;
                case 3:
                    System.out.println("Enter here: ");
                    movieTitle = sc.next();

            }
            // TODO: add call to previous view
        } while (choice < 8);
    }

    public void updateShowtime() {

    }

    public void deleteShowtime() {

    }
    public ShowtimeManager loadObject() {}

    public void saveObject() {}
}
