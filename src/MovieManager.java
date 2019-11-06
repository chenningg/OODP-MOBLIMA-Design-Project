import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieManager {
    private static MovieManager single_instance = null;

    private List<Movie> movies = new ArrayList<>();

    private MovieManager() {}

    public static MovieManager getInstance()
    {
        if (single_instance == null)
            single_instance = new MovieManager();
        return single_instance;
    }

    Scanner sc = new Scanner(System.in);

    public void addMovie() {
        Movie newMovie = new Movie();
        List<Genre> genreList = new ArrayList<>();
        List<String> castList = new ArrayList<>();
        List<MovieFormat> formatList = new ArrayList<>();

        // System.out.println("Enter movieID: ");
        // newMovie.setMovieID(sc.next());
        System.out.println("Enter movie title: ");
        newMovie.setTitle(sc.next());

        System.out.println("Enter number of genres: ");
        int numGenres = sc.nextInt();
        System.out.println("Enter the genres: ");
        for (int i=0;i<numGenres;i++)
        {
            System.out.println("Enter the genre: ");
            String userGenre = sc.next();
            genreList.add(Genre.valueOf(userGenre));
        }
        newMovie.setGenres(genreList);

        System.out.println("Enter director name: ");
        newMovie.setDirector(sc.next());

        System.out.println("Enter length of cast: ");
        int castLength = sc.nextInt();
        for (int i=0;i<castLength;i++)
        {
            System.out.println("Enter cast member: ");
            String castName = sc.next();
            castList.add(castName);
        }
        newMovie.setCast(castList);

        System.out.println("Enter synopsis: ");
        newMovie.setSynopsis(sc.next());

        System.out.println("Enter movie rating: ");
        String movieRating = sc.next();
        newMovie.setMovieRating(MovieRating.valueOf(movieRating));

        System.out.println("Enter number of movie formats: ");
        int formatLength = sc.nextInt();
        for (int i=0;i<formatLength;i++)
        {
            System.out.println("Enter movie format: ");
            String format = sc.next();
            formatList.add(MovieFormat.valueOf(format));
        }
        newMovie.setMovieFormats(formatList);

        System.out.println("Enter movie duration: ");
        newMovie.setMovieDuration(sc.nextFloat());

        System.out.println("Enter showing status: ");
        String showStatus = sc.next();
        newMovie.setShowingStatus(ShowingStatus.valueOf(showStatus));

        System.out.println("Enter release date (format DD/MM/YYYY): ");
        String releaseDate = sc.next();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        LocalDate date = LocalDate.parse(releaseDate, dateFormat);
        newMovie.setReleaseDate(date);

        movies.add(newMovie);
    }

    public void editMovie() {
        System.out.println("Enter movieID: ");
        String movieID = sc.next();
        for (Movie movie : movies) {
            if (movie.getMovieID().equalsIgnoreCase(movieID)) {
                do {
                    System.out.println("(1) Edit movieID");
                    System.out.println("(2) Edit title");
                    System.out.println("(3) Edit genres");
                    System.out.println("(4) Edit director");
                    System.out.println("(5) Edit cast");
                    System.out.println("(6) Edit synopsis");
                    System.out.println("(7) Edit rating");
                    System.out.println("(8) Edit formats");
                    System.out.println("(9) Edit duration");
                    System.out.println("(10) Edit showing status");
                    System.out.println("(11) Edit release date");
                    int choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            
                    }
                }
            }
        }
    }

    public void deleteMovie() {}

    public void peekMovie() {}

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Movie> searchMovies(String movieName) {
        List<Movie> foundMovies = new ArrayList<>();
        String lowerCaseName = movieName.toLowerCase();

        // for-each loop
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(lowerCaseName)) {
                foundMovies.add(movie);
            }
        }
        return foundMovies;
    }

    public void displayMovieDetails(Movie movie) {
        System.out.print("Movie Title: ");
        System.out.println(movie.getTitle());
        System.out.print("Showing Status: ");
        System.out.println(movie.getShowingStatus());
        System.out.print("Synopsis: ");
        System.out.println(movie.getSynopsis());
        System.out.print("Director: ");
        System.out.println(movie.getDirector());
        System.out.print("Cast: ");
        for(int i=0;i<movie.getCast().size();i++){
            System.out.println(movie.getCast().get(i));
        }
    }

    public Movie getMovieByID(String movieID) {
        for (Movie movie : movies) {
            if (movie.getMovieID().equalsIgnoreCase(movieID)) {
                return movie;
            }
        }
        return null;
    }
}
