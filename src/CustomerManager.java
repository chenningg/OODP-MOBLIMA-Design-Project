import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerManager implements ResetSelf{

	//private CustomerLookup customerLookup;
	private Map<String, String> mobileHash;
	private Map<String, String> emailHash;
	private Map<String, CustomerAccount> idHash;
	private Scanner sc= new Scanner(System.in);
	private CustomerAccount cust;


	//Singleton
	private static CustomerManager single_instance = null;
	
	//constructor
	CustomerManager(){
		this.emailHash= new HashMap<String,String>();
		this.mobileHash= new HashMap<String,String>();
		this.idHash= new HashMap<String,CustomerAccount>();
		this.loadCustomers();
	}
	
	public static CustomerManager getInstance()
	{
		if (single_instance==null)
			single_instance= new CustomerManager();
		return single_instance;
	}
	
	
	//searching accounts
	public CustomerAccount emailToCustomer(String email) {
		if (idHash.containsKey(emailHash.get(email)))
			return idHash.get(emailHash.get(email));
		else
			return null;
	}
	
	public CustomerAccount mobileToCustomer(String mobileNo) {
		if (idHash.containsKey(mobileHash.get(mobileNo)))
			return idHash.get(mobileHash.get(mobileNo));
		else 
			return null;
	}
	
	public void printPastBookingByEmail() {
		System.out.println("Enter your email address: ");
		String email= sc.next();
		//checks if email valid, then retrieves booking history of associated account
		if (validateEmail(email))
			printBookingHistory(emailToCustomer(email));
		}
	
	public void printPastBookingByMobile() {
		System.out.println("Enter your mobile number:");
		String mobileNo= sc.next();
		//checks if mobileNo valid, then prints booking history of associated account
		if (validateMobileNo(mobileNo))
			printBookingHistory(mobileToCustomer(mobileNo));
	}
	
	public void printBookingHistory(CustomerAccount custToPrint) {
		Booking booking;
		if (custToPrint==null)
			System.out.println("No records found.\n");
		else {
			System.out.println("Previous Bookings:");
			for (int i=0;i<custToPrint.getBookingHistoryID().size();i++) {
				//search directory for each booking file according to ID
				booking= loadBooking(custToPrint.getBookingHistoryID().get(i));
				
				if (booking!=null)
					booking.displayBooking();
			}
		}
	}
	
	//search directory for booking files
	public Booking loadBooking(String bookingID) {
		Booking booking=null;
		
		String folderPath = ProjectRootPathFinder.findProjectRootPath() + "/data/bookings";
		File[] files= getAllFiles(folderPath);
		
		for (int i=0; i<files.length;i++)
		{
			String filePath= files[i].getPath();
			if (filePath.contains(bookingID))
				booking= (Booking)SerializerHelper.deSerializeObject(filePath);
		}
		return booking;
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
		if (idHash.containsKey(mobileHash.get(mobileNo))) {
			currCustomer = mobileToCustomer(mobileNo);
		}
			
		//else, create newCustomer and addBooking
		else {
			currCustomer= new CustomerAccount(name,email,mobileNo);
			currCustomer.setCustomerID(IDHelper.getLatestID("customer"));
			emailHash.put(email, currCustomer.getCustomerID());
			mobileHash.put(mobileNo, currCustomer.getCustomerID());
		}
		setCust(currCustomer);
	}
	
	public void storeBooking(String bookingID) {
		getCust().addBookingID(bookingID);
		idHash.put(getCust().getCustomerID(), getCust());
		save();
		resetSelf();
	}

	//saver and loaders
	private void save() {
		String filePath = ProjectRootPathFinder.findProjectRootPath();
    	filePath = filePath + "/data/customers/customer_" + getCust().getCustomerID() + ".dat"; 
		SerializerHelper.serializeObject(getCust(), filePath);
	}
	
	//Loads Customer Data
	public void loadCustomers(){
		String folderPath = ProjectRootPathFinder.findProjectRootPath() + "/data/customers";
		File[] files= getAllFiles(folderPath);
		
		for (int i=0; i<files.length;i++)
		{
			String filePath= files[i].getPath();
			setCust((CustomerAccount) SerializerHelper.deSerializeObject(filePath));
			emailHash.put(getCust().getEmail(), getCust().getCustomerID());
			mobileHash.put(getCust().getmobileNo(), getCust().getCustomerID());
			idHash.put(getCust().getCustomerID(), getCust());
		}
		
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
	
	// Loads all text files in the specified folder and returns the list of files
	public File[] getAllFiles(String folderPath) {
		
		// Finds folder and gets a list of all files in folder
		File directory = new File(folderPath);
		return(directory.listFiles());
	}	
}
