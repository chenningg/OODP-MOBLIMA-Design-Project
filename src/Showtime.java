import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Showtime{

    private String showtimeID;
    private LocalDate date;
    private LocalTime time;


    //Getters & Setters
    public void getShowtimeID(){
        this.showtimeID = showtimeID;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }


    public void setShowtimeID(String showtimeID) {
        this.showtimeID = showtimeID;
    }
}