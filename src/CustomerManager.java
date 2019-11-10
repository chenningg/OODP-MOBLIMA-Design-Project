import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

public class CustomerManager implements ResetSelf {

	private Map<String,String> emailToMobileNo = new HashMap<String,String>();
	private Map<String, CustomerAccount> mobileToCustomer= new HashMap<String,CustomerAccount>();
	Scanner sc= new Scanner(System.in);
	private CustomerAccount cust;


	//Singleton
	private static CustomerManager single_instance = null;
	
	//constructor
	private CustomerManager() {}
	
	public static CustomerManager getInstance()
	{
		if (single_instance==null)
			single_instance= new CustomerManager();
		return single_instance;
	}
	
	
	//searching accounts
	public void printPastBookingByEmail() {
		System.out.println("Enter your email address: ");
		String email= sc.next();
		//checks if email valid, then retrieves booking history of associated account
		if (validateEmail(email))
			printBookingHistory(mobileToCustomer.get(emailToMobileNo.get(email)));
		}
	
	public void printPastBookingByMobile() {
		System.out.println("Enter your mobile number:");
		String mobileNo= sc.next();
		//checks if mobileNo valid, then prints booking history of associated account
		if (validateMobileNo(mobileNo))
			printBookingHistory(mobileToCustomer.get(mobileNo));
	}
	
	public void printBookingHistory(CustomerAccount cust) {
		System.out.println("Previous Bookings:");
		//prints each booking held in customerAccount
		for (int i=0;i<cust.getBookingHistory().size();i++) {
			System.out.printf(i+1+ "." + cust.getBookingHistory().get(i)+ "\n");
		}
	}
	
	public List<Booking> getBookingHistory(CustomerAccount customerAccount) {
		return customerAccount.getBookingHistory();
	}
	
	// Validate mobile number of user, check if all numeric and is 8 digits long (Assume Singapore)
	protected Boolean validateMobileNo(String mobileNo) {	
		char[] chars = mobileNo.toCharArray();

		// Check length
		if (mobileNo.length() != 8) {
			System.out.println("Please enter a valid 8 digit mobile number (no country code).");
			return false;
		}
		
	    for (char c : chars) {
	        if(!Character.isDigit(c)) {
	        	System.out.println("Your mobile number must be purely numeric. Please try again.");
	            return false;
	        }
	    }

	    return true;
	}
	
	
	// Checks if email address is valid
	protected Boolean validateEmail(String email) {
		// Check length
		if (email.length() > 100) {
			System.out.println("Sorry, your email is too long. Please use a shorter email.");
			return false;
		}
		
		// Match email against pattern using regex
		String pattern = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
		if(!(email.matches(pattern))) {
			System.out.println("Sorry, that email is invalid. Please try again.");
			return false;
		}
		
		return true;
	}
	
	//Create, Read, Update, Delete
	public void updateCustomer(String name, String email, String mobileNo)
	//should be called during BookingManager.makeBooking
	{
		CustomerAccount currCustomer;
		
		//if current customer exists, addBooking to customerAccount
		if (mobileToCustomer.containsKey(mobileNo)) {
			currCustomer = mobileToCustomer.get(mobileNo);
		}
			
		//else, create newCustomer and addBooking
		else {
			currCustomer= new CustomerAccount();
			currCustomer.setEmail(email);
			currCustomer.setMobileNo(mobileNo);
			currCustomer.setName(name);
			
			emailToMobileNo.put(email, mobileNo);
			mobileToCustomer.put(mobileNo, currCustomer);
		}
		setCust(currCustomer);
	}
	
	public void storeBooking(Booking booking) {
		getCust().addBooking(booking);
		resetSelf();
	}
	
	public void resetSelf() {
		setCust(null);
	}
	
	//getters and setters
	public CustomerAccount getCust() {
		return cust;
	}

	public void setCust(CustomerAccount cust) {
		this.cust = cust;
	}

}
