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
        System.out.println("Enter movieID: ");
        newMovie.setMovieID(sc.next());
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
