import java.util.Scanner;

public class TransactionManager {
	private Transaction transaction;
	
	//constructor
	TransactionManager(){
		this.transaction= null;
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
    	s.close();
    	this.transaction= tr;
	}
	
	public void getDetails() {
		
	}
	
	public void makeTransaction() {
		confirmBooking();
		
	}
	
	public void storeTransaction() {
		
	}
	
	public void confirmBooking() {
		
	}
}
