import java.util.Scanner;

public class TransactionManager {
	private Transaction transaction=null;
	
	//constructor
	TransactionManager(){
		this.transaction= null;
	}
	TransactionManager(Transaction transaction){
		this.transaction= transaction;
	}
	
	//methods
	public void startTransaction(Booking booking){
		Transaction tr= new Transaction();
		tr.setBooking(booking);
		tr.setTransactionID();
    	System.out.println("Please enter your credit card number");
    	Scanner s= new Scanner(System.in);
    	String creditCardNo = s.next();
    	tr.setCreditCardNo(creditCardNo);
	}
	
	public void getDetails() {
		
	}
	
	public void makeTransaction() {
		
	}
	
	public void storeTransaction() {
		
	}
	
	public void confirmBooking() {
		
	}
}
