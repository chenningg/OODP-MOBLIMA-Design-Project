import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Booking {
	
    //Attributes
	
    private String bookingID;
    private ArrayList<Ticket> tickets = new ArrayList<>();
    private double totalPrice = 0;
    private LocalDate date;
    private LocalTime time;
    private String movieName;
    private int hallNo;
    private String cineplexName;
    private String bookerMobileNo = null;
    private String bookerEmail = null;

    
    //Methods

    
    
    // Getters
    
	public String getBookingID() {
		return bookingID;
	}
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public LocalDate getDate() {
		return date;
	}
	public LocalTime getTime() {
		return time;
	}
	public String getMovieName() {
		return movieName;
	}
	public int getHallNo() {
		return hallNo;
	}
	public String getCineplexName() {
		return cineplexName;
	}
	public String getBookerMobileNo() {
		return bookerMobileNo;
	}
	public String getBookerEmail() {
		return bookerEmail;
	}
	
	
	// Setters
	
	public void setBookingID(String bookingID) {
		this.bookingID = bookingID;
	}
	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public void setHallNo(int hallNo) {
		this.hallNo = hallNo;
	}
	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}
	public void setBookerMobileNo(String bookerMobileNo) {
		this.bookerMobileNo = bookerMobileNo;
	}
	public void setBookerEmail(String bookerEmail) {
		this.bookerEmail = bookerEmail;
	}
}
