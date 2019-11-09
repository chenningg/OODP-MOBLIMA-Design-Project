import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

public class TicketManager implements ResetSelf {
	
	// Variables
    private ArrayList<Ticket> selectedTickets = new ArrayList<Ticket>();
    private HashMap<TicketType, Integer> ticketCount = new HashMap<TicketType, Integer>();
    private HashMap<TicketType, Double> ticketPrices = new HashMap<TicketType, Double>();
    private Scanner sc = new Scanner(System.in);
	

	// Singleton & Constructor
 	private static TicketManager single_instance = null;
 	
 	private TicketManager() {}
	
	public static TicketManager getInstance()
	{
	    if (single_instance == null) {
	    	 single_instance = new TicketManager();
	    }	        
	    return single_instance;
	}

	
	// Methods
	
	// Starts ticket selection
    public void startTicketSelection(Showtime showtime, List<String> selectedSeats) {
    	
    	Boolean exit = false;
    	int maxTickets = selectedSeats.size(); // Total number of tickets available for selection
    	int ticketChoices = TicketType.values().length; // Number of ticket choices available
    	int ticketsLeft = maxTickets; // Tracks number of tickets left for selection
    	int choice;
    	
    	while (!exit) {
    		
    		ticketsLeft = maxTickets - getSelectedTickets().size();
    		
    		// Prints out ticket type selection menu
    		System.out.printf("You have selected %d seats. %d tickets remaining to select:\n", ticketsLeft);
    		
    		for (int i = 1; i <= ticketChoices; i++) {
    			System.out.printf("%d. %s\n", i, TicketType.values()[i-1].toString());
    		}
    		System.out.printf("%d. Clear selected tickets\n", ticketChoices+1);
    		System.out.printf("%d. Proceed to payment\n", ticketChoices+2);
    		System.out.println("0. Back to seats selection");		
    		
    		choice = sc.nextInt();
    		
    		// Clear selected tickets and ticketCount, return to seats
    		if (choice == 0) { 	// Exit
    			exit = true;
    			resetSelf();
    		}
    		// Clear selected tickets but try again  	
    		else if (choice == ticketChoices+1) { 		
    			resetSelf();
    			System.out.printf("Ticket selections cleared.");
    		}
    		// Check all seats have tickets, then proceed to payment
    		else if (choice == ticketChoices+2) { 		
    	    	
    			// Check number of tickets = number of selected seats
    			if (ticketsLeft == 0) {
    			
	    	    	// Update all tickets in selection with seatID information
	    	    	for (int j = 0; j < getSelectedTickets().size(); j++) {
	    	    		getSelectedTickets().get(j).setSeatID(selectedSeats.get(j));
	    	    	}
	    	    	
	    	    	// PROCEED TO PAYMENT, passes on selected seats as well as a count of ticket types
	    	    	TransactionManager.getInstance().startTransaction(getTicketPrices(), getTicketCount());
    	    	}
    			else {
    				System.out.printf("Not enough tickets selected! %d tickets remaining to select.\n", ticketsLeft);
    			}
    		}
    		// Add ticket selection based on its type. Update ticketCount and ticketSelection.
    		else if (choice >= 1 && choice <= ticketChoices) {
    			System.out.printf("How many %s tickets would you like to purchase? (Max %d):\n", TicketType.values()[choice-1].toString(), ticketsLeft);
    			
    			int count = sc.nextInt();

    			// Too little or too many tickets booked, go back to ticket selection menu
    			if (count < 1 || count > ticketsLeft) {
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
    
    
    // Adds a new ticket selection
    public void addTicketSelection(TicketType ticketType, int count) {
    	for (int i = 0; i < count; i++) {
    		Ticket newTicket = new Ticket(ticketType);
    		
    		// Update ticket price based on cinema type, holiday and time
    		newTicket.setTicketPrice(newTicket.getTicketPrice());
    		
    		getSelectedTickets().add(newTicket);
    	}
    	getTicketCount().put(ticketType, count);
    }
   
    
    // Injects ticket selection information into BookingManager's booking when event is raised, and reset itself
    public void confirmTicketSelection() {
    	BookingManager.getInstance().getBooking().setTickets(selectedTickets);
    	resetSelf();
    }
    
    
    // Resets self
    public void resetSelf() {
    	getSelectedTickets().clear();
    	getTicketCount().clear();
    	getTicketPrices().clear();
    }
    
    
    // Getters
 	public ArrayList<Ticket> getSelectedTickets() {return selectedTickets;}
 	public HashMap<TicketType, Integer> getTicketCount() {return ticketCount;}
 	public HashMap<TicketType, Double> getTicketPrices() {return ticketPrices;}
}
