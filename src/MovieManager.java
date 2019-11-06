import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieManager {
    private static MovieManager single_instance = null;

    private List<Movie> movies;

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


    }

    public void editMovie() {}

    public void deleteMovie() {}

    public void peekMovie() {}

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Movie> searchMovies(String movieName) {}

    public void displayMovieDetails(Movie movie) {}

    public Movie getMovieByID(int movieID) {}
}
