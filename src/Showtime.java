import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Showtime{

    private String showtimeID;
    private LocalDate date;
    private LocalTime time;
    private Movie movie;
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
    public LocalDate getDate() {return date;}
    public LocalTime getTime() {return time;}
    public Movie getMovie() {return movie;}
    public Cinema getCinema() {return cinema;}
    public Cineplex getCineplex() {return cineplex;}
    public CinemaStatus getCinemaStatus() {return cinemaStatus;}

    //Setters
    public void setShowtimeID(String showtimeID) {this.showtimeID = showtimeID;}
    public void setDate(LocalDate date) {this.date = date;}
    public void setTime(LocalTime time) {this.time = time;}
    public void setMovie(Movie movie) {this.movie = movie;}
    public void setCinema(Cinema cinema) {this.cinema = cinema;}
    public void setCineplex(Cineplex cineplex) {this.cineplex = cineplex;}
    public void setCinemaStatus(CinemaStatus cinemaStatus) {this.cinemaStatus = cinemaStatus;}

}