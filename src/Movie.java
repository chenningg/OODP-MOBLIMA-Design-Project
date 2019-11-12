import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable{
    private String movieID;
    private String title;
    private ArrayList<Genre> genres;
    private String director;
    private ArrayList<String> cast;
    private String synopsis;
    private MovieRating movieRating;
    private ArrayList<MovieFormat> movieFormats;
    private int movieDuration;
    private ArrayList<Review> movieReviews = null;
    private float averageReviewScore;
    private int totalReviewNo;
    private float totalReviewScore;
    private ShowingStatus showingStatus = ShowingStatus.COMING_SOON;
    private LocalDate releaseDate;
    private long ticketsSold;
    private double grossProfit;
    private static final long serialVersionUID = 5L;

    public Movie(){}

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

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
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

    public ArrayList<MovieFormat> getMovieFormats() {
        return movieFormats;
    }

    public void setMovieFormats(ArrayList<MovieFormat> movieFormats) {
        this.movieFormats = movieFormats;
    }

    public float getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }

    public ArrayList<Review> getMovieReviews() {
        return movieReviews;
    }

    public void setMovieReviews(ArrayList<Review> movieReviews) {
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

    public void addMovieReview(Review review) {
        this.movieReviews.add(review);
    }

}