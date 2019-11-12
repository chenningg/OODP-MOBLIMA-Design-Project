import java.util.Scanner;

public class DriverApp {
	public static void main(String[] args) {
		int choice;
		Scanner sc = new Scanner(System.in); 
		
		do {
	        System.out.println("======================= MOBLIMA APP =======================\n"+
	                "| 1. Customer App                                          |\n"+
	                "| 2. Staff App                                             |\n"+
	                "| 0. Quit App                                              |\n"+
	                "===========================================================");
		
	        System.out.println("Enter choice: ");
	        choice = sc.nextInt();
	        
	        switch (choice) {
		        case 1:
		        	 CustomerApp.getInstance().displayCustomerMenu();
		        	break;
		        case 2:
		        	StaffApp.getInstance().displayLoginMenu();
		        	break;
		        case 0:
		        	System.out.println("Thank you for using our MOBLIMA APP");
		        	break;
	        	default:
	        		System.out.println("Please enter an option between 0-2");
	        		break;
	        }
		} while (choice != 0);
		
		sc.close();
	}
}
