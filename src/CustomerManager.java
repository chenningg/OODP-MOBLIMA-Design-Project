import java.util.Scanner;

public class CustomerManager implements ResetSelf{

	private CustomerLookup customerLookup;
	private Scanner sc= new Scanner(System.in);
	private CustomerAccount cust;


	//Singleton
	private static CustomerManager single_instance = null;
	
	//constructor
	CustomerManager(){
		CustomerLookup serializedObject = this.load();
		if (serializedObject != null) {
			this.customerLookup = serializedObject;
		} else {
			this.customerLookup = new CustomerLookup();
			this.save();
		}
	}
	
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
			printBookingHistory(customerLookup.getCustfromEmail(email));
		}
	
	public void printPastBookingByMobile() {
		System.out.println("Enter your mobile number:");
		String mobileNo= sc.next();
		//checks if mobileNo valid, then prints booking history of associated account
		if (validateMobileNo(mobileNo))
			printBookingHistory(customerLookup.getCustfromMobile(mobileNo));
	}
	
	public void printBookingHistory(CustomerAccount custToPrint) {
		if (custToPrint==null)
			System.out.println("No records found.\n");
		else {
			System.out.println("Previous Bookings:");
			//prints each booking held in customerAccount
			for (int i=0;i<custToPrint.getBookingHistory().size();i++) {
				// Get booking from customer history and display it
				custToPrint.getBookingHistory().get(i).displayBooking();
			}
		}
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
		if (customerLookup.getMobileHash().containsKey(mobileNo)) {
			currCustomer = customerLookup.getCustfromMobile(mobileNo);
		}
			
		//else, create newCustomer and addBooking
		else {
			currCustomer= new CustomerAccount(name,email,mobileNo);
			customerLookup.update(email, mobileNo,currCustomer);
		}
		save();
		setCust(currCustomer);
	}
	
	public void storeBooking(Booking booking) {
		getCust().addBooking(booking);
		save();
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

	//saver and loaders
	private CustomerLookup load() {
			String filePath = ProjectRootPathFinder.findProjectRootPath() + "/data/CustomerManager/customerlookup.dat";
			return (CustomerLookup) SerializerHelper.deSerializeObject(filePath);
		}
	
	private void save() {
		String filePath = ProjectRootPathFinder.findProjectRootPath() + "/data/CustomerManager/customerlookup.dat";
		SerializerHelper.serializeObject(this.customerLookup, filePath);
	}
}
