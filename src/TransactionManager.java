import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class TransactionManager implements ResetSelf {
	
	private Transaction transaction = null;
	private String bookerMobileNo;
	private String bookerEmail;
	private Scanner sc = new Scanner(System.in);
	
	// Singleton & Constructor
 	private static TransactionManager single_instance = null;
 	
 	private TransactionManager() {}
	
	public static TransactionManager getInstance()
	{
	    if (single_instance == null) {
	    	 single_instance = new TransactionManager();
	    }	        
	    return single_instance;
	}

	
	// Methods
	
	// Start a new transaction with selected seats and selected tickets
	public void startTransaction(Map<TicketType, Double> ticketPrices, Map<TicketType, Integer> ticketCount) {
		
		// Create new transaction
		setTransaction(new Transaction());

		// Show prices
		displayPrices(selectedTickets, ticketCount);
	}
	
	
	// Displays pricing information and total price
	public void displayPrices(List<Ticket> selectedTickets, Map<TicketType, Integer> ticketCount) {
		
		// Print out selected ticket prices inclusive of GST and total amount
		System.out.println("Please check your booking prices below:");
		System.out.printf("%-20s%-20s%s", "Item", "Quantity", "Net Price");
		for (Map.Entry<TicketType, Integer> item : ticketCount.entrySet()) {
			System.out.printf("%-20sx%-20d\n", item.getKey().toString() + " TICKET", item.getValue());
			System.out.printf("%f", )
                                			
		}
	}
	
	
	// Collects user info
	public Boolean validateMobileNo() {
		return false;
	}
	
	
	// Checks if email address is valid
	public Boolean validateEmail() {
		return false;
	}
	
	
	// Self reset
	public void resetSelf() {
		setTransaction(null);
		setBookerMobileNo(null);
		setBookerEmail(null);
	}
	
	
	// Getters
	
	public Transaction getTransaction() {return transaction;}
	public String getBookerMobileNo() {return bookerMobileNo;}
	public String getBookerEmail() {return bookerEmail;}
	
	
	// Setters
	
	public void setTransaction(Transaction transaction) {this.transaction = transaction;}
	public void setBookerMobileNo(String bookerMobileNo) {this.bookerMobileNo = bookerMobileNo;}
	public void setBookerEmail(String bookerEmail) {this.bookerEmail = bookerEmail;}
}
