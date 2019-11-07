import java.util.ArrayList;
import java.util.Scanner;

class BookingManager {
    // Variables
    private Showtime showtime = null;
    private ArrayList<String> seatingPlan;
    private ArrayList<String> selectedSeats = new ArrayList<String>();
    private ArrayList<Ticket> selectedTickets = new ArrayList<Ticket>();
	private Booking booking = null;
    private Scanner sc = new Scanner(System.in);
    
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
    	setSeatingPlan(copySeatingPlan(baseShowtime.getCinema().getCinemaLayout()));
    	
    	// Show them booking menu until they exit
    	Boolean exit = false;
    	while (!exit) {
    		// Show the seating plan
        	displaySeats(getSeatingPlan());
        	
        	System.out.println("Please choose a choice:");
        	System.out.println("1. Select a seat");
        	System.out.println("2. Deselect a seat");
        	System.out.println("3. Confirm");
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
    public void displaySeats(ArrayList<String> seatingPlan) {
    	int i = 0;
		while (i < seatingPlan.size()) {
			System.out.println(seatingPlan.get(i));
			i++;
		}	
    }
    
    // Create a local deep copy of showtime's seats
    public ArrayList<String> copySeatingPlan(ArrayList<String> seats) {
    	ArrayList<String> seatsCopy = new ArrayList<String>();
    	int i = 0;
		while (i < seats.size()) {
			seatsCopy.add(seats.get(i));
			i++;
		}
    	return seatsCopy;
    }
    
    public void ticketSelection() {
    	Boolean exit = false;
    	int count = getSelectedSeats().size(); // Tracks number of tickets available for selection
    	int ticketChoices = TicketType.values().length; // Number of ticket choices available
    	
    	while (!exit) {
    		// Prints out ticket type selection menu
    		// TODO TICKET DISCUSSION
    		System.out.printf("You have selected %s" );
    		System.out.println("Please select tickets:");
    		for (int i = 1; i <= ticketChoices; i++) {
    			System.out.printf("%d. %s\n", i, TicketType.values()[i-1].toString());
    		}
    		System.out.printf("%s. Clear selected tickets\n", ticketChoices+1);
    		System.out.println("0. Cancel");
    		
    		switch(sc.nextInt()) {
	    		case 0:
	    			exit = true;
	    			break;
	    		case 1: // Select ticket
	    			addTicketSelection();
	    			break;
	    		case 2: // Remove ticket
	    			deleteTicketSelection();
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
    
    public void setSeatingPlan(ArrayList<String> seatingPlan) {
		this.seatingPlan = seatingPlan;
	}
    
    // Getters
	public Showtime getShowtime() {
		return showtime;
	}
	
    public ArrayList<String> getSeatingPlan() {
		return seatingPlan;
	}

	public ArrayList<String> getSelectedSeats() {
		return selectedSeats;
	}

	public ArrayList<Ticket> getSelectedTickets() {
		return selectedTickets;
	}
}