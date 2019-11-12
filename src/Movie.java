import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable{
	// Attributes
	private String movieID;
    private String title;
    private List<Genre> genres;
    private String director;
    private List<String> cast; 
    private String synopsis;
    private MovieRating movieRating;
    private List<MovieFormat> movieFormats;
    private int movieDuration;
    private List<Review> movieReviews;
    private float averageReviewScore;
    private int totalReviewNo;
    private float totalReviewScore;
    private ShowingStatus showingStatus;
    private LocalDate releaseDate;
    private long ticketsSold;
    private double grossProfit;
    

    // Constructor
    public Movie(){
    	this.genres = new ArrayList<Genre>();
    	this.cast = new ArrayList<String>();
    	this.movieFormats = new ArrayList<MovieFormat>();
    	this.movieReviews = new ArrayList<Review>();
    	
    }

    
    // Getters
    public String getMovieID() {return movieID;}    
    public String getTitle() {return title;}
    public List<Genre> getGenres() {return genres;}
    public String getDirector() {return director;}
    public List<String> getCast() {return cast;}
    public String getSynopsis() {return synopsis;}
    public MovieRating getMovieRating() {return movieRating;}
    public List<MovieFormat> getMovieFormats() {return movieFormats;}
    public float getMovieDuration() {return movieDuration;}
    public List<Review> getMovieReviews() {return movieReviews;}
    public float getAverageReviewScore() {return averageReviewScore;}
    public int getTotalReviewNo() {return totalReviewNo;}
    public float getTotalReviewScore() {return totalReviewScore;}
    public ShowingStatus getShowingStatus() {return showingStatus;}
    public LocalDate getReleaseDate() {return releaseDate;}
    public long getTicketsSold() {return ticketsSold;}
    public double getGrossProfit() {return grossProfit;}


    // Setters
    public void setMovieID(String movieID) {this.movieID = movieID;}
    public void setTitle(String title) {this.title = title;}
    public void setGenres(ArrayList<Genre> genres) {this.genres = genres;}
    public void setDirector(String director) {this.director = director;}
    public void setCast(ArrayList<String> cast) {this.cast = cast;}
    public void setSynopsis(String synopsis) {this.synopsis = synopsis;}
    public void setMovieRating(MovieRating movieRating) {this.movieRating = movieRating;}
    public void setMovieFormats(ArrayList<MovieFormat> movieFormats) {this.movieFormats = movieFormats;}
    public void setMovieDuration(int movieDuration) {this.movieDuration = movieDuration;}
    public void setMovieReviews(ArrayList<Review> movieReviews) {this.movieReviews = movieReviews;}
    public void setAverageReviewScore(float averageReviewScore) {this.averageReviewScore = averageReviewScore;}
    public void setTotalReviewNo(int totalReviewNo) {this.totalReviewNo = totalReviewNo;}
    public void setTotalReviewScore(float totalReviewScore) {this.totalReviewScore = totalReviewScore;}
    public void setShowingStatus(ShowingStatus showingStatus) {this.showingStatus = showingStatus;}
    public void setReleaseDate(LocalDate releaseDate) {this.releaseDate = releaseDate;}
    public void setTicketsSold(long ticketsSold) {this.ticketsSold = ticketsSold;}
    public void setGrossProfit(double grossProfit) {this.grossProfit = grossProfit;}
 
    
    
    // 
    public void addMovieReview(Review review) {
        this.movieReviews.add(review);
    }

}