import java.util.ArrayList;
import java.util.List;

public class CustomerAccount{

    private String mobileNo;
    private String email;
    private List<Booking> bookingHistory= new ArrayList<Booking>();
    private String name=null;

	//constructor
    CustomerAccount(){}
    
    public void addBooking(Booking booking) {
    	bookingHistory.add(booking);
    }
    
    
    public List<Booking> getBookingHistory() {
    	return bookingHistory;
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
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}