import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BookingManager {
	// Singleton
	private static BookingManager single_instance = null;

	// Constructor
    private BookingManager() {}

    public static BookingManager getInstance()
    {
        if (single_instance == null)
            single_instance = new BookingManager();
        return single_instance;
    }
    
    // Variables
    private Showtime showtime = null;
    private List<String> selectedSeats = new ArrayList<String>();
    private List<Ticket> selectedTickets = new ArrayList<Ticket>();
    private Booking booking = null;
    
    // Methods
    
    // Starts a booking by showing available seats and allows user to select seats based on showtime
    public void startBooking(Showtime showtime) {
    	System.out.println("Displaying seats...");
    	displaySeats(showtime.getCinema().getSeats());
    }
    
    // Prints out seats in a nice manner upon being given a 2D array of seats
    public void displaySeats(Seat[][] seatingPlan) {
    	int width = seatingPlan[0].length;
    	int height = seatingPlan.length;
    	int screenIndex = width/2 - 2;
    	int entranceIndex = width/2 - 1;
    	
    	// First row is screen, print screen centralized
    	for (int i = 0; i < entranceIndex; i++) {
    		System.out.printf(" ");
    	}
    	System.out.printf("SCREEN\n");
    	
    	// Print aisles and seats
    	for (int row = 1; row < height; row++) {
            for (int col = 0; col < width; col++) {
            	if (seatingPlan[row][col] == '')
            }
        }		
    }
}