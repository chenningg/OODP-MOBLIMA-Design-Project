import java.util.Scanner;
import java.util.HashMap;
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
	public void startTransaction(HashMap<TicketType, Integer> ticketCount) {
		
		// Create new transaction
		setTransaction(new Transaction());

		// Show prices
		displayPrices();
	}
	
	
	// Displays pricing information and total price
	public void displayPrices() {
		
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
