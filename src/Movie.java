import java.time.LocalDate;
import java.util.List;

public class Movie {
    private String movieID;
    private String title;
    private List<Genre> genres;
    private String director;
    private List<String> cast;
    private String synopsis;
    private MovieRating movieRating;
    private List<MovieFormat> movieFormats;
    private float movieDuration;
    private List<Review> movieReviews = null;
    private float averageReviewScore;
    private int totalReviewNo;
    private float totalReviewScore;
    private ShowingStatus showingStatus = ShowingStatus.COMING_SOON;
    private LocalDate releaseDate;
    private long ticketsSold;
    private double grossProfit;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public MovieRating getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(MovieRating movieRating) {
        this.movieRating = movieRating;
    }

    public List<MovieFormat> getMovieFormats() {
        return movieFormats;
    }

    public void setMovieFormats(List<MovieFormat> movieFormats) {
        this.movieFormats = movieFormats;
    }

    public float getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(float movieDuration) {
        this.movieDuration = movieDuration;
    }

    public List<Review> getMovieReviews() {
        return movieReviews;
    }

    public void setMovieReviews(List<Review> movieReviews) {
        this.movieReviews = movieReviews;
    }

    public float getAverageReviewScore() {
        return averageReviewScore;
    }

    public void setAverageReviewScore(float averageReviewScore) {
        this.averageReviewScore = averageReviewScore;
    }

    public int getTotalReviewNo() {
        return totalReviewNo;
    }

    public void setTotalReviewNo(int totalReviewNo) {
        this.totalReviewNo = totalReviewNo;
    }

    public float getTotalReviewScore() {
        return totalReviewScore;
    }

    public void setTotalReviewScore(float totalReviewScore) {
        this.totalReviewScore = totalReviewScore;
    }

    public ShowingStatus getShowingStatus() {
        return showingStatus;
    }

    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(long ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public double getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(double grossProfit) {
        this.grossProfit = grossProfit;
    }
}