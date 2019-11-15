import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerAccount implements Serializable{

	private static final long serialVersionUID = 7L;
	private String name=null;
	private String customerID;
    private String mobileNo;
    private String email;
    private List<String> bookingHistory= new ArrayList<String>();
   

	//constructor
    CustomerAccount(){}
    
    CustomerAccount(String name, String email, String mobileNo){
    	this.email= email;
    	this.mobileNo= mobileNo;
    	this.name= name;
    }
    
    public void addBookingID(String bookingID) {
    	bookingHistory.add(bookingID);
    }
    
    
    public List<String> getBookingHistoryID() {
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

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	

}