import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class BookingManager {
    // Variables
    private Showtime showtime = null;
    private ArrayList<String> seatingPlan;
    private ArrayList<String> selectedSeats = new ArrayList<String>();
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
        				TicketManager.getInstance().startTicketSelection();
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
    
    // Check for adjacent seats on the same row, no gaps in between same row seats
    public Boolean checkAdjacentSeats() {
    	
    }
    
    public void deleteSeatSelection() {
    	System.out.println("Please enter seat to deselect (e.g. C6):");
    }
    
    
    // Setters
    public void setShowtime(Showtime showtime) {this.showtime = showtime;}   
    public void setSeatingPlan(ArrayList<String> seatingPlan) {this.seatingPlan = seatingPlan;}
	public void setBooking(Booking booking) {this.booking = booking;}
    
	
    // Getters
	public Showtime getShowtime() {return showtime;}
    public ArrayList<String> getSeatingPlan() {return seatingPlan;}
	public ArrayList<String> getSelectedSeats() {return selectedSeats;}
	public Booking getBooking() {return booking;}
}