import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManager {
	private ArrayList<CustomerAccount> customers = new ArrayList<CustomerAccount>();
	Scanner sc= new Scanner(System.in);
	
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
	public CustomerAccount searchCustEmail(String email) {
		for (CustomerAccount customerAcc : customers) {
			if (customerAcc.getEmail().contains(email))
				return customerAcc;
		}
		return null;
	}
	
	public CustomerAccount searchCustMobile(String mobileNo) {
		for (CustomerAccount customerAcc : customers) {
			if (customerAcc.getmobileNo().contains(mobileNo))
				return customerAcc;
		}
		return null;
	}
	
	//Create, Read, Update, Delete
	
	public void addCustomer()
	//should be called during BookingManager.makeBooking
	{
		CustomerAccount newCustomer= new CustomerAccount();
		
		System.out.println("Enter Email:");
		newCustomer.setEmail(sc.next());
		
		System.out.println("Enter Mobile Number:");
		newCustomer.setMobileNo(sc.next());
		
		//adds current booking and transaction
		newCustomer.addBooking();
		newCustomer.addTransaction();
		
		customers.add(newCustomer);
	}
	
	public ArrayList<CustomerAccount> getCustomers(){
		return customers;
	}
	

}
