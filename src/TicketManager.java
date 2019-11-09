import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TicketManager {
	// Variables
    private ArrayList<Ticket> selectedTickets = new ArrayList<Ticket>();
    private HashMap<String, Integer> ticketCount = new HashMap<String, Integer>();
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
	
	// Deals with ticket selection
    public void startTicketSelection(ArrayList<String> selectedSeats) {
    	Boolean exit = false;
    	int maxTickets = selectedSeats.size(); // Tracks number of tickets available for selection
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
    
    
    // Adds a new ticket selection
    public void addTicketSelection(TicketType ticketType, int count) {
    	for (int i = 0; i < count; i++) {
    		Ticket newTicket = new Ticket(ticketType);
    		getSelectedTickets().add(newTicket);
    	}
    	getTicketCount().put(ticketType.toString(), count);
    }
    
    
    // Setters
    
    
    // Getters
	public ArrayList<Ticket> getSelectedTickets() {return selectedTickets;}
	public HashMap<String, Integer> getTicketCount() {return ticketCount;}
}
