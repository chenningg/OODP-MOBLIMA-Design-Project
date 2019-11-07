import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BookingManager {
    // Variables
    private Showtime showtime = null;
    private char[][] seatingPlan;
    private List<String> selectedSeats = new ArrayList<String>();
    private List<Ticket> selectedTickets = new ArrayList<Ticket>();
	private Booking booking = null;
    private Scanner sc = new Scanner(System.in);
    private String selection;
    
    // Singleton
 	private static BookingManager single_instance = null;

 	// Constructor
 	private BookingManager() {}
	
	public static BookingManager getInstance()
	{
	    if (single_instance == null) {
	    	 single_instance = new BookingManager();
	    }	        
	    return single_instance;
	}
	    
    // Methods

    // Starts a booking by showing available seats and allows user to select seats based on a copy of showtime
    public void startBooking(Showtime baseShowtime) {
    	// Create a deep copy of showtime seats so we don't affect the original until booking completes
    	setShowtime(baseShowtime);
    	setSeatingPlan(copySeatingPlan(baseShowtime.getCinema().getSeats()));
    	
    	// Show them booking menu until they exit
    	Boolean exit = false;
    	while (!exit) {
    		// Show the seating plan
        	displaySeats(getSeatingPlan());
        	
        	System.out.println("Please choose a choice:");
        	System.out.println("1. Select a seat");
        	System.out.println("2. Deselect a seat");
        	System.out.println("3. Ticket selection");
        	System.out.println("0. Exit");

        	switch(sc.nextInt()) {
        		case 0:
        			exit = true;
        			break;
        		case 1: // Select seat
        			addSeatSelection();
        			break;
        		case 2: // Deselect seat
        			deleteSeatSelection();
        			break;
        		case 3: // Ticket selection, requires at least a seat selection
        			// If no seats selected, don't allow ticket selection
        			if (getSelectedSeats().size() <= 0) {
        				System.out.println("No seats selected. Please select a seat before choosing tickets.");
        			}
        			else {
        				// Book tickets
        				ticketSelection();
        			}
        			break;
        		default:
        			System.out.println("Invalid choice entered. Please try again.");
        	}
    	}
    }
    
    // Prints out seating plan in a nice manner upon being given a 2D array of chars
    public void displaySeats(char[][] seatingPlan) {
    	int width = seatingPlan[0].length;
    	int height = seatingPlan.length;
    	
    	// Print entire layout
    	for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
            	System.out.printf("%c", seatingPlan[row][col]);
            }
            System.out.println(); // Line break
        }		
    }
    
    public char[][] copySeatingPlan(char[][] seats) {
    	// Create a local deep copy of showtime's seats
    	int height = seats.length;
    	int width = seats[0].length;
    	char[][] seatsCopy = new char[height][width];
    	for (int row = 0; row < height; row++) {
    		for (int col = 0; col < width; col++) {
    			seatsCopy[row][col] = seats[row][col];
    		}
    	}
    	return seatsCopy;
    }
    
    public void ticketSelection() {
    	Boolean exit = false;
    	int count = getSelectedSeats().size(); // Tracks number of tickets available for selection
    	int ticketChoices = TicketType.values().length; // Number of ticket choices available
    	
    	while (!exit) {
    		// Prints out ticket type selection
    		System.out.println("Please select ticket type:");
    		for (int i = 1; i <= ticketChoices; i++) {
    			System.out.printf("%d. %s\n", i, TicketType.values()[i-1]);
    		}
    		
    		
    	}
    }
    
    // Setters
    public void addSeatSelection() {
		System.out.println("Please enter your seat selection (e.g. C6):");
    }
    
    public void deleteSeatSelection() {
    	System.out.println("Please enter seat to deselect (e.g. C6):");
    }
    
    public void addTicketSelection() {
    	
    }
    
    public void deleteTicketSelection() {
    	
    }

    public void setShowtime(Showtime showtime) {
		this.showtime = showtime;
	}
    
    public void setSeatingPlan(char[][] seatingPlan) {
		this.seatingPlan = seatingPlan;
	}
    
    // Getters
	public Showtime getShowtime() {
		return showtime;
	}
	
    public char[][] getSeatingPlan() {
		return seatingPlan;
	}

	public List<String> getSelectedSeats() {
		return selectedSeats;
	}

	public List<Ticket> getSelectedTickets() {
		return selectedTickets;
	}
}