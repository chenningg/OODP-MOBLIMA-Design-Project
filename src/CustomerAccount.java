import java.util.ArrayList;
import java.util.List;

public class CustomerAccount {

    private String mobileNo;
    private String email;
    private ArrayList<Booking> bookingHistory= new ArrayList<Booking>();
    private ArrayList<Transaction> transactionHistory= new ArrayList<Transaction>();
    
    //constructor
    CustomerAccount(){}
    
    public void addBooking() {
    	bookingHistory.add(BookingManager.getInstance().getBooking());
    }
    
    public void addTransaction() {
    	transactionHistory.add(TransactionManager.getInstance().getTransaction());
    }
    
    public List<Booking> getBookingHistory() {
    	return bookingHistory;
    }
    
    public List<Transaction> getTransactionHistory(){
    	return transactionHistory;
    }

	public String getmobileNo() {
		return email;
	}

	public String getEmail() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}