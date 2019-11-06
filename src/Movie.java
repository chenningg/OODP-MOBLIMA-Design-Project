import java.time.LocalDateTime;

public class Movie {
    private String movieID;
    private String title;
    private Genre[] genres;
    private String director;
    private String[] cast;
    private String synopsis;
    private MovieRating movieRating;
    private MovieFormat[] movieFormats;
    private float movieDuration;
    private Review[] movieReviews = null;
    private float averageReviewScore;
    private int totalReviewNo;
    private float totalReviewScore;
    private ShowingStatus showingStatus = ShowingStatus.COMING_SOON;
    private LocalDateTime releaseDate;
    private long ticketsSold;
    private double grossProfit;


}
