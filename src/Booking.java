import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.io.*;

public class Booking {
	
    //Attributes
	
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
    
    // Gets the latest ID for this booking from file
    public String getLatestID() {
    	
    	String latestID = String.format("%08ld", 00000000);
    	
    	try {
			// Get filepath
			String filePath = ProjectRootPathFinder.findProjectRootPath();
			
			if (filePath == null) {
				throw new IOException("Cannot find root");
			} else {
				filePath = filePath + "/data/ids/booking_id.txt";
			}
			
			// Open file and traverse it						
			FileReader frStream = new FileReader( filePath );
			BufferedReader brStream = new BufferedReader( frStream );
			String inputLine;

			inputLine = brStream.readLine(); // read in a line
			if (inputLine == null) {
				latestID = String.format("%08ld", 00000000);
			}
			else {
				latestID = inputLine;
			}
			
			brStream.close(); // Close file
			
			// Open file in write mode
			FileWriter fwStream = new FileWriter(filePath, false); // Overwrite file
		    BufferedWriter bwStream = new BufferedWriter(fwStream);
		    
		    String newLatestID = String.format("%08ld", Integer.valueOf(latestID) + 1);
		    
		    bwStream.write(newLatestID);
			
		    bwStream.close(); // Close file
		    
		    return latestID;
			
		} catch ( FileNotFoundException e ) {
			System.out.println( "Error opening the input file!" + e.getMessage() );
			System.exit( 0 );
		} catch ( IOException e ) {
			System.out.println( "IO Error!" + e.getMessage() );
			e.printStackTrace();
			System.exit( 0 );
		}
    	
		return latestID;           
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
	
	public void setBookingID() {
		this.bookingID = getLatestID();
	}
	
	public void setTickets(ArrayList<Ticket> tickets) {this.tickets = tickets;}
	public void setTransaction(Transaction transaction) {this.transaction = transaction;}
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
	public void setMovieName(String movieName) {this.movieName = movieName;}
	public void setHallNo(int hallNo) {this.hallNo = hallNo;}
	public void setCineplexName(String cineplexName) {this.cineplexName = cineplexName;}
}
