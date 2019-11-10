import java.io.Serializable;
import java.time.LocalDateTime;

public class Showtime implements Serializable {
    private static final long serialVersionUID = 2L;
    private String showtimeID;
    private LocalDateTime dateTime;
    private Movie movie;
    private MovieFormat movieFormat;
    private Cinema cinema;
    private Cineplex cineplex;
    private CinemaStatus cinemaStatus;


    //Methods

    public void updateCinemaStatus() {
    	double percentageFilled = getCinema().getOccupiedSeatsNo() / getCinema().getTotalSeatNo();

    	if (percentageFilled <= 50.0) {
    		setCinemaStatus(CinemaStatus.AVAILABLE);
    	}
    	else if (percentageFilled < 100.0) {
    		setCinemaStatus(CinemaStatus.SELLING_FAST);
    	}
    	else {
    		setCinemaStatus(CinemaStatus.SOLD_OUT);
    	}
    }


    //Getters

    public String getShowtimeID() {return showtimeID;}
    public LocalDateTime getDateTime() {return dateTime;}
    public Movie getMovie() {return movie;}
    public MovieFormat getMovieFormat() {return movieFormat;}
    public Cinema getCinema() {return cinema;}
    public Cineplex getCineplex() {return cineplex;}
    public CinemaStatus getCinemaStatus() {return cinemaStatus;}

    //Setters
    public void setShowtimeID(String showtimeID) {this.showtimeID = showtimeID;}
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setMovie(Movie movie) {this.movie = movie;}
    public void setMovieFormat(MovieFormat movieFormat) {this.movieFormat = movieFormat;}
    public void setCinema(Cinema cinema) {this.cinema = cinema;}
    public void setCineplex(Cineplex cineplex) {this.cineplex = cineplex;}
    public void setCinemaStatus(CinemaStatus cinemaStatus) {this.cinemaStatus = cinemaStatus;}

}
