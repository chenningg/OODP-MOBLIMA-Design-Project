import java.util.List;

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

    public void addMovie() {

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
