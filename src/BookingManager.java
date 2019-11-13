import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class BookingManager implements ResetSelf {
    // Variables
    private List<String> seatingPlan;
    private List<String> selectedSeats = new ArrayList<String>();
    private HashMap<Character, ArrayList<Integer>> rowChecker = new HashMap<Character, ArrayList<Integer>>(); // Checks if a seat has been booked in a specific row
    private Scanner sc = new Scanner(System.in);
    private Booking booking = null; // Current booking to make
    private Showtime showtime = null; // Current showtime selected
    public Boolean exit = false;
    
    
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
    	exit = false;
    	while (!exit) {
    		
    		// If no more seats left, terminate
    		if (baseShowtime.getCinemaStatus() == CinemaStatus.SOLD_OUT) {
    			System.out.println("Sorry. This showtime is sold out. Please select another showtime.");
    			exit = true;
    			resetSelf();
    			return;
    		}
    		
    		// Show the seating plan
        	displaySeats(getSeatingPlan());
        	
        	System.out.println("Please choose a choice:");
        	System.out.println("1. Select a seat");
        	System.out.println("2. Deselect a seat");
        	System.out.println("3. Confirm and go to ticket selection");
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
        				TicketManager.getInstance().startTicketSelection(getShowtime(), getSelectedSeats());
        			}
        			break;
        		default:
        			System.out.println("Invalid choice entered. Please try again.");
        	}
    	}
    }
    

	// Prints out seating plan in a nice manner upon being given a List of Strings
    public void displaySeats(List<String> list) {
    	char item;
    	String rowRef;
    	String[] symbols = {" ", "X", "S"}; // Symbols for seat status display
    	
    	// Iterate through List of Strings
    	for (int row = 0; row < getSeatingPlan().size(); row++) {
    		rowRef = getSeatingPlan().get(row);
    		
    		// Check if this row has seats. If not, just print whole row straight.
    		if (!(Character.isAlphabetic(rowRef.charAt(0)))) { // No seats
    			System.out.println(rowRef);
    		}
    		else { // Seats present
    			for (int col = 0; col < rowRef.length(); col++) {
        			item = rowRef.charAt(col);

        			// Check if current item is a seat. Prints corresponding symbol according to seat status.
        			if (Character.isDigit(item)) {
        				System.out.printf("%s", symbols[Character.getNumericValue(item)]);
        			}
        			// Else just print the character
        			else {
        				System.out.printf(Character.toString(item));
        			}
        		}
    		}
    	}
    }
    
    
    // Create a local deep copy of showtime's seats
    public ArrayList<String> copySeatingPlan(List<String> list) {
    	ArrayList<String> seatsCopy = new ArrayList<String>();
    	int i = 0;
		while (i < list.size()) {
			seatsCopy.add(list.get(i));
			i++;
		}
    	return seatsCopy;
    }
    
    
    // Add a seat selection. We need to check that all seats added are adjacent to each other, and are unoccupied.
    public void addSeatSelection() {
		System.out.println("Please enter a seat selection (e.g. C6):");
		String selection = sc.next().toUpperCase();
		
		// Check if seat selection is allowable. If it is, update rowChecker and selectedSeats
		if (allowSeatSelection(selection)) {
			char seatRow = selection.charAt(0); // Alphabet row of seatID
			int seatCol = Integer.valueOf(selection.substring(1, selection.length())); // Integer column of seatID
			
			// Add the new seat selection to selectedSeats
			getSelectedSeats().add(selection);
			
			// If no existing seats selected for a row, create the row in rowChecker
			if (!(getRowChecker().containsKey(seatRow))) {
				getRowChecker().put(seatRow, new ArrayList<Integer>());		
			}
			
			// Add in the selected seat in the row for rowChecker
			getRowChecker().get(seatRow).add(seatCol);
			
			// Update seating plan
	    	updateSeatingPlan(selection, "addSelection");
		}
		
		// If invalid selection, error is automatically generated by allowSeatSelection() function.
    }
    
    
    // Checks if seat selection is valid
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
									if (rowChecker.containsKey(seatRow)) {
										ArrayList<Integer> rowSeats= rowChecker.get(seatRow);
										
										// Selected seat column must be +/- one of any existing entry
										for (int i = 0; i < rowSeats.size(); i++) {
											if (Math.abs(rowSeats.get(i) - seatCol) == 1) {
												// Seat exists, is unoccupied, and is adjacent to a previous selection, allow selection.
												return true;
											}
										}
										
										// Seat is not adjacent to previous selections in the same row, return false
										System.out.println("Seat selection invalid. Please do not leave spaces between seats.");
										return false;
									}
									// Seat exists, and is unoccupied. No previous selection in row, allow selection.
									else {
										return true;
									}					
								}
								// Seat is already occupied, disallow selection
								else {
									System.out.println("Seat selection invalid. This seat has already been selected or is otherwise occupied.");
									return false;
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
    	System.out.println("Please enter a seat selection to remove (e.g. C6):");
		String selection = sc.next().toUpperCase();
		
		// Check if seat selection is allowable. If it is, update rowChecker and selectedSeats
		if (allowSeatDeletion(selection)) {
			char seatRow = selection.charAt(0); // Alphabet row of seatID
			int seatCol = Integer.valueOf(selection.substring(1, selection.length())); // Integer column of seatID
		
			// Remove seat selection from selectedSeats
			getSelectedSeats().remove(selection);
			
			// Remove the seat from rowChecker
			getRowChecker().get(seatRow).remove(seatCol);
			
			// If the row is empty after removing this seat, then remove row entry from rowChecker
			if (getRowChecker().get(seatRow).size() <= 0) {
				getRowChecker().remove(seatRow);
			}
			
			// Update seating plan
			updateSeatingPlan(selection, "deleteSelection");
		}
		
		// If invalid selection, error is automatically generated by allowSeatDeletion() function.
    }
    
    
    // Checks if seat selection removal is valid
    public Boolean allowSeatDeletion(String seatID) {
    	// First we check if entered seat ID is valid aka starts with alphabet and has an integer (capped at 2 digits)
    	if (seatID.matches("[A-Z]\\d{1,2}")) {
			// If seatID entered is valid, check if seat exists in currently selected seats
    		if (getSelectedSeats().contains(seatID)) {
    			char seatRow = seatID.charAt(0); // Alphabet row of seatID
    			int seatCol = Integer.valueOf(seatID.substring(1, seatID.length())); // Integer column of seatID
    			
    			// We need to check if the seat exists in a row between selections to ensure no gaps are formed
    			ArrayList<Integer> rowRef = getRowChecker().get(seatRow);
    			
    			// If seat for deletion is in between two other seats, do not allow deletion
    			if (rowRef.contains(seatCol+1) && rowRef.contains(seatCol-1)) {
    				System.out.println("The seatID specified could not be deselected. Please do not leave spaces between seat selections.");
					return false;
    			}
    			// Else, the seat is either an edge seat or is a solitary seat in that row, allow deselection
    			else {
    				return true;
    			}
    		}
    		// Seat doesn't exist as a selection
    		else {
    			System.out.println("SeatID does not match any currently selected seats. Please try again.");
    			return false;
    		}
		}

    	// If we reach here, either the input is invalid, the row doesn't exist, or the column of seat doesn't exist.
    	System.out.println("Invalid seatID input. Please try again.");
    	return false;
    }
    
    
    // Update seating plan List. Operation can be: addSelection, deleteSelection or confirmSelection
    public void updateSeatingPlan(String seatID, String operation) {
    	char seatRow = seatID.charAt(0); // Alphabet row of seatID
		int seatCol = Integer.valueOf(seatID.substring(1, seatID.length())); // Integer column of seatID
		String seatModifier;
		
		switch(operation) {
			case "addSelection":
				seatModifier = "2";
				break;
			case "deleteSelection":
				seatModifier = "0";
				break;
			case "confirmSelection":
				seatModifier = "1";
				break;
			default:
				System.out.println("Error! Operation parameter for function updateSeatingPlan() in BookingManager is invalid.");
				return; // Terminate
		}
		
		// Iterate until we find the seat's row and col index position in the seatingPlan List
    	for (int row = 0; row < getSeatingPlan().size(); row++) {
    		String rowRef = getSeatingPlan().get(row);
    		if (rowRef.charAt(0) == seatRow) {
    			int seatCount = 0; // Counts seats from start of row
    		 	for (int col = 1; col < rowRef.length(); col++) {
    		 		if (Character.isDigit(rowRef.charAt(col))) {
    		 			seatCount += 1;
    		 			if (seatCount == seatCol) {
    		 				// Modify the row string to include the seat's new status
    		 				String updatedRow = rowRef.substring(0, col) + seatModifier + rowRef.substring(col+1);
    		 				
    		 				// Replace current row
    		 				rowRef = updatedRow;
    		 				return; // Terminate function
    		 			}
    		 		}
	    		}
    		}
    	}
    }
    
    
    // Once booking is confirmed and payment is made, we raise an event to let other parts know to inject information into this booking
    public void makeBooking() {
    	// Since booking is confirmed, we update all selected seats to be confirmed seats (occupied)
    	for (int i = 0; i < getSelectedSeats().size(); i++) {
    		updateSeatingPlan(getSelectedSeats().get(i), "confirmBooking");
    	}
    	
    	// We then update the current showtime REFERENCE with the new seating plan, and update the showtime fill status
    	showtime.getCinema().setCinemaLayout(getSeatingPlan());
    	showtime.updateCinemaStatus();
    	
    	// Save the new showtimes status
    	ShowtimeManager.getInstance().save();
    	
    	// We create a new booking and fill it up with the finalized information before storing it
    	setBooking(new Booking());
    	
    	// We now fill up the booking's tickets, transaction, bookerName, bookerMobileNo and bookerEmail
    	// Note that this also resets both these Managers (calls their respective reset functions)
    	// In these functions, we also update the movie's ticket sales and gross profits
    	TicketManager.getInstance().confirmTicketSelection();
    	TransactionManager.getInstance().confirmTransaction(); 	
    	
    	// Now we have to update the rest of booking: bookingID, movieName, hallNo and cineplexName
    	
    	// Update the booking with the showtime's date and time
    	getBooking().setDateTime(getShowtime().getDateTime());
    	
    	// Update booking's movieName, cineplexName and hallNo
    	getBooking().setMovieName(showtime.getMovie().getTitle());
    	getBooking().setCineplexName(showtime.getCineplex().getCineplexName());
    	getBooking().setHallNo(showtime.getCinema().getHallNo());
    	
    	// Finally, we can generate a unique ID for this booking
    	getBooking().setBookingID();
    	
    	// We store the booking in our CustomerAccount
    	CustomerManager.getInstance().storeBooking(getBooking());
    	
    	// We can then send this booking off to store as a serialized file
    	String filePath = ProjectRootPathFinder.findProjectRootPath();
    	filePath = filePath + "/data/bookings/booking_" + getBooking().getBookingID() + ".txt";
    	
    	SerializerHelper.serializeObject(getBooking(), filePath);
    	
    	// Finally, reset this instance
    	resetSelf();
    }
    
    
    // Resets all variables of this singleton instance (e.g. if user presses back)
    public void resetSelf() {
    	setShowtime(null);
		setBooking(null);
		setSeatingPlan(null);
    	getSelectedSeats().clear();
		getRowChecker().clear();
		exit = true;
    }
    
    
    // Setters
    public void setShowtime(Showtime showtime) {this.showtime = showtime;}
    public void setBooking(Booking booking) {this.booking = booking;}
    public void setSeatingPlan(ArrayList<String> seatingPlan) {this.seatingPlan = seatingPlan;}
    
	
    // Getters
	public Showtime getShowtime() {return showtime;}
	public Booking getBooking() {return booking;}
    public List<String> getSeatingPlan() {return seatingPlan;}
	public List<String> getSelectedSeats() {return selectedSeats;}
	public HashMap<Character, ArrayList<Integer>> getRowChecker() {return rowChecker;}
}