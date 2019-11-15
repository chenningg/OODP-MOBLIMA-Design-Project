import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Booking implements Serializable {
	
    //Attributes
	
	private static final long serialVersionUID = 6L;
    private String bookingID;
    private List<Ticket> tickets = new ArrayList<Ticket>();
    private Transaction transaction;
    private LocalDateTime dateTime;
    private String movieName;
    private int hallNo;
    private String cineplexName;

    
    //Methods
    
    // Displays this booking in formatted form
    public void displayBooking() {
    	System.out.println("=============================================================================");
    	System.out.printf("Booking ID: %s\n", getBookingID());
    	System.out.printf("Cineplex: %s\n", getCineplexName());
    	System.out.printf("Movie: %s\n", getMovieName());
    	System.out.printf("Hall: %d\n", getHallNo());
    	
    	// Print showtime date and time
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd MMM yyyy, hh.mma");
    	System.out.printf("Showtime: %s\n", getDateTime().format(formatter));
    	
    	// Print tickets
    	System.out.printf("Seats booked: ");
    	for (int i = 0; i < getTickets().size(); i++) {
    		System.out.printf("%s, ", getTickets().get(i).getSeatID());
    	}
    	System.out.println();
    	System.out.println("=============================================================================");
    }
    
    
    // Getters
    
	public String getBookingID() {return bookingID;}
	public List<Ticket> getTickets() {return tickets;}
	public Transaction getTransaction() {return transaction;}
    public LocalDateTime getDateTime() {return dateTime;}
	public String getMovieName() {return movieName;}
	public int getHallNo() {return hallNo;}
	public String getCineplexName() {return cineplexName;}
	
	
	// Setters
	
	public void setBookingID(String latestID) {this.bookingID = latestID;}	
	public void setTickets(ArrayList<Ticket> tickets) {this.tickets = tickets;}
	public void setTransaction(Transaction transaction) {this.transaction = transaction;}
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
	public void setMovieName(String movieName) {this.movieName = movieName;}
	public void setHallNo(int hallNo) {this.hallNo = hallNo;}
	public void setCineplexName(String cineplexName) {this.cineplexName = cineplexName;}
}
