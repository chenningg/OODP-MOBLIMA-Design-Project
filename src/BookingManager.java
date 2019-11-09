import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class BookingManager implements ResetSelf {
    // Variables
    private ArrayList<String> seatingPlan;
    private ArrayList<String> selectedSeats = new ArrayList<String>();
    private HashMap<Character, Integer> rowChecker = new HashMap<Character, Integer>(); // Checks if a seat has been booked in a specific row
    private Scanner sc = new Scanner(System.in);
    private Booking booking = null; // Current booking to make
    private Showtime showtime = null; // Current showtime selected
    
    
    // Singleton & Constructor
 	private static BookingManager single_instance = null;
 	
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
    public void startSeatSelection(Showtime baseShowtime) {
    	// Create a deep copy of showtime seats so we don't affect the original until booking completes
    	setShowtime(baseShowtime); // Contain reference to selected showtime
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
        			resetSelf();
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
		
		// Check if seat selection is allowable
		if (allowSeatSelection(selection)) {
			
		}
		
		// If invalid selection, return error
		System.out.println("Invalid seat ID entered. Please try again.");
    }
    
    
    // Check for adjacent seats on the same row, no gaps in between same row seats
    public Boolean allowSeatSelection(String seatID) {
    	// First we check if entered seat ID is valid aka starts with alphabet and has an integer (capped at 2 digits)
    	if (seatID.matches("[A-Z]\\d{1,2}")) {
			// If seatID entered is valid, check if seat exists
			char seatRow = seatID.charAt(0); // Alphabet row of seatID
			int seatCol = Integer.valueOf(seatID.substring(1, seatID.length())); // Integer column of seatID
			int seatCount = 0; // Count of seat index in a row
			String rowRef;
			
			// Iterate through rows of seating plan to find seat row alphabet
			for (int row = 1; row < getSeatingPlan().size(); row++) {				
				rowRef = getSeatingPlan().get(row);
				
				// If we find a match for seat row
				if (rowRef.charAt(0) == seatRow) {
					
					// We then check if seat exists at the specified column. Ignore 0 index as it's the row alphabet.
					for (int col = 1; col < rowRef.length(); col++) {
						
						// When we encounter a digit (aka a seat) add it to seatCount and match against seatCol.
						if (Character.isDigit(rowRef.charAt(col))) {
							seatCount += 1;
							
							// Check if current seat we are at matches seatCol of selected seat (aka seat exists)
							if (seatCol == seatCount) {
								
								// Check if seat is unoccupied
								if (rowRef.charAt(col) == '0') {
									
									// Check if there are existing bookings in this row. 
									// Only allow selection if current selection is adjacent to any existing bookings.
									
									
								}
								
							}
						}
					}
				}
			}
		}

    	// If we reach here, either the input is invalid, the row doesn't exist, or the column of seat doesn't exist.
    	System.out.println("Invalid seatID input. Please try again.");
    	return false;
    }
    
    
    // Deletes a seat selection
    public void deleteSeatSelection() {
    	System.out.println("Please enter seat to deselect (e.g. C6):");
    }
    
    
    // Once booking is confirmed and payment is made, listen to event raised and call this to create and store booking
    public void makeBooking() {
    	
    }
    
    
    // Resets all variables of this singleton instance (e.g. if user presses back)
    public void resetSelf() {
    	setShowtime(null);
		setBooking(null);
		setSeatingPlan(null);
    	getSelectedSeats().clear();
		getRowChecker().clear();
    }
    
    
    // Setters
    public void setShowtime(Showtime showtime) {this.showtime = showtime;}
    public void setBooking(Booking booking) {this.booking = booking;}
    public void setSeatingPlan(ArrayList<String> seatingPlan) {this.seatingPlan = seatingPlan;}
    
	
    // Getters
	public Showtime getShowtime() {return showtime;}
	public Booking getBooking() {return booking;}
    public ArrayList<String> getSeatingPlan() {return seatingPlan;}
	public ArrayList<String> getSelectedSeats() {return selectedSeats;}
	public HashMap<Character, Integer> getRowChecker() {return rowChecker;}
}