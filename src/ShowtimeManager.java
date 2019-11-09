import java.util.ArrayList;

public class ShowtimeManager {
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


    // Methods
    public void displayMovieShowtimes()
    {

    }
}
