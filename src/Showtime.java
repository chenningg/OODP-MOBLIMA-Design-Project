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
    private ArrayList <String> seatLayout = cinema.getCinemaLayout(); // booking manager to override with each booking
    private int seatsFilled = 0; // booking manager to update this count for easy reference too

    //Methods

    //Getters
    public void getShowtimeID(){
        this.showtimeID = showtimeID;
    }
    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return time;
    }
    public Movie getMovie() {
        return movie;
    }
    public Cinema getCinema() {
        return cinema;
    }
    public Cineplex getCineplex() {
        return cineplex;
    }
    public CinemaStatus getCinemaStatus() {
        return cinemaStatus;
    }
    public int getSeatsFilled() { return seatsFilled; }

    //Setters
    public void setShowtimeID(String showtimeID) {
        this.showtimeID = showtimeID;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }
    public void setSeatsFilled(int seatsFilled) {
        this.seatsFilled = seatsFilled;
    }
}