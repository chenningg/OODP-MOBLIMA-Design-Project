import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Booking {
	
    //Attributes
	
    private String bookingID;
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private Transaction transaction;
    private LocalDateTime dateTime;
    private String movieName;
    private int hallNo;
    private String cineplexName;
    private String bookerMobileNo = null;
    private String bookerEmail = null;

    
    //Methods

    
    
    // Getters
    
	public String getBookingID() {return bookingID;}
	public ArrayList<Ticket> getTickets() {return tickets;}
	public Transaction getTransaction() {return transaction;}
    public LocalDateTime getDateTime() {return dateTime;}
	public String getMovieName() {return movieName;}
	public int getHallNo() {return hallNo;}
	public String getCineplexName() {return cineplexName;}
	public String getBookerMobileNo() {return bookerMobileNo;}
	public String getBookerEmail() {return bookerEmail;}
	
	
	// Setters
	
	public void setBookingID(String bookingID) {this.bookingID = bookingID;}
	public void setTickets(ArrayList<Ticket> tickets) {this.tickets = tickets;}
	public void setTransaction(Transaction transaction) {this.transaction = transaction;}
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
	public void setMovieName(String movieName) {this.movieName = movieName;}
	public void setHallNo(int hallNo) {this.hallNo = hallNo;}
	public void setCineplexName(String cineplexName) {this.cineplexName = cineplexName;}
	public void setBookerMobileNo(String bookerMobileNo) {this.bookerMobileNo = bookerMobileNo;}
	public void setBookerEmail(String bookerEmail) {this.bookerEmail = bookerEmail;}
}
