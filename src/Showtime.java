import java.io.Serializable;
import java.time.LocalDateTime;

public class Showtime implements Serializable {
    private static final long serialVersionUID = 2L;
    private String showtimeID;
    private LocalDateTime dateTime;
    private String movieID;
    private MovieFormat movieFormat;
    private Cinema cinema;
    private String cineplexName;
    private CinemaStatus cinemaStatus;


    //Methods

    public void updateCinemaStatus() {
    	double percentageFilled = (double) getCinema().getOccupiedSeatsNo() / (double) getCinema().getTotalSeatNo();

    	if (percentageFilled <= 0.50) {
    		setCinemaStatus(CinemaStatus.AVAILABLE);
    	}
    	else if (percentageFilled < 1) {
    		setCinemaStatus(CinemaStatus.SELLING_FAST);
    	}
    	else {
    		setCinemaStatus(CinemaStatus.SOLD_OUT);
    	}
    }


    //Getters

    public String getShowtimeID() {return showtimeID;}
    public LocalDateTime getDateTime() {return dateTime;}
    public String getMovieID() {return movieID;}
    public MovieFormat getMovieFormat() {return movieFormat;}
    public Cinema getCinema() {return cinema;}
    public String getCineplexName() {return cineplexName;}
    public CinemaStatus getCinemaStatus() {return cinemaStatus;}

    //Setters
    public void setShowtimeID(String showtimeID) {this.showtimeID = showtimeID;}
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setMovie(String movieID) {this.movieID = movieID;}
    public void setMovieFormat(MovieFormat movieFormat) {this.movieFormat = movieFormat;}
    public void setCinema(Cinema cinema) {this.cinema = cinema;}
    public void setCineplexName(String cineplexName) {this.cineplexName = cineplexName;}
    public void setCinemaStatus(CinemaStatus cinemaStatus) {this.cinemaStatus = cinemaStatus;}

}
