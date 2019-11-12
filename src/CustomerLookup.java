import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CustomerLookup implements Serializable{
	
	private static final long serialVersionUID = 8L;
	private Map<String, CustomerAccount> mobileHash;
	private Map<String,CustomerAccount> emailHash;

	//Singleton
	private static CustomerLookup single_instance = null;
	
	//constructor
	CustomerLookup(){
		this.setMobileHash();
		this.setEmailHash();
	}
	
	//get instance
	public static CustomerLookup getInstance()
	{
		if (single_instance==null)
			single_instance= new CustomerLookup();
		return single_instance;
	}
	
	//update
	public void update(String email, String mobile, CustomerAccount cust) {
		mobileHash.put(mobile,cust);
		emailHash.put(email,cust);
	}

	//getters
	public Map<String,CustomerAccount> getMobileHash(){return mobileHash;}
	public Map<String,CustomerAccount> getEmailHash(){return emailHash;}
	
	//Using email as key, find value (customerID)
	public CustomerAccount getCustfromEmail(String email) {
		if (this.getEmailHash().containsKey(email))
			return this.getEmailHash().get(email);
		else
			return null;
	}
	
	//using mobileNo as key, find value(customerID)
	public CustomerAccount getCustfromMobile(String mobileNo) {
		if (this.getMobileHash().containsKey(mobileNo))
			return this.getMobileHash().get(mobileNo);
		else
			return null;
	}

	//specialised setters
	private void setMobileHash() {
		this.mobileHash= new HashMap<String,CustomerAccount>();
	}
	
	private void setEmailHash() {
		this.emailHash= new HashMap<String,CustomerAccount>();
	}
	
}
