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
    private List<String> reviews;
    private double averageReviewScore;
    private int totalReviewNo;
    private double totalReviewScore;
    private ShowingStatus showingStatus;
    private LocalDate releaseDate;
    private long ticketsSold;
    private double grossProfit;
    private List<String> showtimeIDs;
    

    // Constructor
    public Movie(){
    	this.genres = new ArrayList<Genre>();
    	this.cast = new ArrayList<String>();
    	this.movieFormats = new ArrayList<MovieFormat>();
    	this.reviews = new ArrayList<String>();
    	this.showtimeIDs = new ArrayList<String>();
    	this.totalReviewNo =0;
    	this.totalReviewScore=0;
    	this.averageReviewScore=0;
    }

    
    // Getters
    public String getMovieID() {return this.movieID;}    
    public String getTitle() {return this.title;}
    public List<Genre> getGenres() {return this.genres;}
    public String getDirector() {return this.director;}
    public List<String> getCast() {return this.cast;}
    public String getSynopsis() {return this.synopsis;}
    public MovieRating getMovieRating() {return this.movieRating;}
    public List<MovieFormat> getMovieFormats() {return this.movieFormats;}
    public double getMovieDuration() {return this.movieDuration;}
    public List<String> getReviews() {return this.reviews;}
    public double getAverageReviewScore() {return this.averageReviewScore;}
    public int getTotalReviewNo() {return this.totalReviewNo;}
    public double getTotalReviewScore() {return this.totalReviewScore;}
    public ShowingStatus getShowingStatus() {return this.showingStatus;}
    public LocalDate getReleaseDate() {return this.releaseDate;}
    public long getTicketsSold() {return this.ticketsSold;}
    public double getGrossProfit() {return this.grossProfit;}
    public List<String> getShowtimeIDs() {return showtimeIDs;}

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
    public void setReviews(ArrayList<String> reviews) {this.reviews = reviews;}
    public void setAverageReviewScore(double averageReviewScore) {this.averageReviewScore = averageReviewScore;}
    public void setTotalReviewNo(int totalReviewNo) {this.totalReviewNo = totalReviewNo;}
    public void setTotalReviewScore(double totalReviewScore) {this.totalReviewScore = totalReviewScore;}
    public void setShowingStatus(ShowingStatus showingStatus) {this.showingStatus = showingStatus;}
    public void setReleaseDate(LocalDate releaseDate) {this.releaseDate = releaseDate;}
    public void setTicketsSold(long ticketsSold) {this.ticketsSold = ticketsSold;}
    public void setGrossProfit(double grossProfit) {this.grossProfit = grossProfit;}
    public void setShowtimeIDs(List<String> showtimeIDs) {this.showtimeIDs = showtimeIDs;}


    // Adders
    public void addMovieReview(String reviewID) {
        this.reviews.add(reviewID);
    }
    
    public void addShowtimeID(String showtimeID) {
    	this.showtimeIDs.add(showtimeID);
    }

    
}