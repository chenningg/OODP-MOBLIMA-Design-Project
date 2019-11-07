import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class BookingManager {
    // Variables
    private Showtime showtime = null;
    private ArrayList<String> seatingPlan;
    private ArrayList<String> selectedSeats = new ArrayList<String>();
    private ArrayList<Ticket> selectedTickets = new ArrayList<Ticket>();
    private HashMap<String, Integer> ticketCount = new HashMap<String, Integer>();
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
        		case 0: // Exit, reset everything
        			exit = true;
        			getSelectedSeats().clear();
        			setShowtime(null);
        			setBooking(null);
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
    
    
    // Deals with ticket selection
    public void ticketSelection() {
    	Boolean exit = false;
    	int maxTickets = getSelectedSeats().size(); // Tracks number of tickets available for selection
    	int ticketChoices = TicketType.values().length; // Number of ticket choices available
    	int choice;
    	
    	while (!exit) {
    		// Prints out ticket type selection menu
    		System.out.printf("You have selected %d seats. %d tickets remaining to select:\n", maxTickets-(getSelectedTickets().size()));
    		for (int i = 1; i <= ticketChoices; i++) {
    			System.out.printf("%d. %s\n", i, TicketType.values()[i-1].toString());
    		}
    		System.out.printf("%d. Clear selected tickets\n", ticketChoices+1);
    		System.out.printf("%d. Proceed to payment\n", ticketChoices+2);
    		System.out.println("0. Cancel");		
    		
    		choice = sc.nextInt();
    		
    		// Clear selected tickets and ticketCount, return to seats
    		if (choice == 0) { 	// Exit
    			exit = true;
    			getSelectedTickets().clear();
    			getTicketCount().clear();
    		}
    		// Clear selected tickets but try again  	
    		else if (choice == ticketChoices+1) { 		
    			getSelectedTickets().clear();
    			getTicketCount().clear();
    			System.out.printf("Ticket selections cleared.");
    		}
    		// Check all seats have tickets, then proceed to payment
    		else if (choice == ticketChoices+2) { 		
    			// TODO PROCEED TO PAYMENT !$!@$!@*%!@%!@*!(@
    		}
    		// Add ticket selection based on its type. Update ticketCount and ticketSelection.
    		else if (choice >= 1 && choice <= ticketChoices) {
    			System.out.printf("How many %s tickets would you like to purchase? (Max %d):\n", TicketType.values()[choice-1].toString(), maxTickets-(getSelectedTickets().size()));
    			int count = sc.nextInt();
    			
    			// Too little or too many tickets booked, go back to ticket selection menu
    			if (count < 1 || count > maxTickets-(getSelectedTickets().size())) {
    				System.out.println("Too many/little tickets selected! If you would like to change ticket selections, please clear selections and try again.");
    				continue;
    			}
    			
    			// Else we update ticketCount and ticketSelection.
    			addTicketSelection(TicketType.values()[choice-1], count);
    		}
    		// Invalid choice
    		else {
    			System.out.println("Invalid choice entered. Please try again.");
    		}
    	}
    }
    
    // Add a seat selection. We need to check that all seats added are adjacent to each other, and are unoccupied.
    public void addSeatSelection() {
		System.out.println("Please enter a seat selection (e.g. C6):");
		String selection = sc.next().toUpperCase();
		
		// Check if seat selection matches format
		if (selection.matches("[A-Z]\\d{1,2}")) {
			// Loop through seating plan to check if seat exists and is available
			char seatRow = selection.charAt(0);
			int seatCol = Integer.valueOf(selection.substring(1, selection.length()));
			
			for (int row = 0; row < getSeatingPlan().size(); row++) {
				// If we find a match for alphabet row
				if (getSeatingPlan().get(row).charAt(0) == seatRow) {
					
				}
			}
		}
		
		// If invalid selection, return error
		System.out.println("Invalid seat ID entered. Please try again.");
    }
    
    
    public void deleteSeatSelection() {
    	System.out.println("Please enter seat to deselect (e.g. C6):");
    }
    
    
    public void addTicketSelection(TicketType ticketType, int count) {
    	for (int i = 0; i < count; i++) {
    		Ticket newTicket = new Ticket(ticketType);
    		getSelectedTickets().add(newTicket);
    	}
    	getTicketCount().put(ticketType.toString(), count);
    }

    
    // Setters
    public void setShowtime(Showtime showtime) {this.showtime = showtime;}   
    public void setSeatingPlan(ArrayList<String> seatingPlan) {this.seatingPlan = seatingPlan;}
	public void setBooking(Booking booking) {this.booking = booking;}
    
	
    // Getters
	public Showtime getShowtime() {return showtime;}
    public ArrayList<String> getSeatingPlan() {return seatingPlan;}
	public ArrayList<String> getSelectedSeats() {return selectedSeats;}
	public ArrayList<Ticket> getSelectedTickets() {return selectedTickets;}
	public HashMap<String, Integer> getTicketCount() {return ticketCount;}
	public Booking getBooking() {return booking;}
}