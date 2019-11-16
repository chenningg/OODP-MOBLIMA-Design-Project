import java.util.Scanner;

public class CustomerApp {
	// Attributes
    private static CustomerApp single_instance = null;
    
    private Scanner sc = new Scanner(System.in);

    private CustomerApp(){}

    public static CustomerApp getInstance()
    {
        if (single_instance == null)
            single_instance = new CustomerApp();
        return single_instance;
    }
	
    public void displayCustomerMenu() {
		int choice;		
		
		do {
            System.out.println(	"============== MOBLIMA CUSTOMER APP ================\n" +
			                    "| 1. View movie listings					        |\n" +
			                    "| 2. View top 5                                    |\n" +
			                    "| 3. Check booking history                         |\n" +
			                    "| 0. Back to MOBLIMA APP                           |\n" +
			                    "====================================================");
	    	
            System.out.println("Enter choice: ");
            
            if (!sc.hasNextInt()) {
        		System.out.println("Invalid input type. Please choose a choice from 0-3.");
        		sc.next(); // Remove newline character
        		choice = -1;
        		continue;
        	}
            
            choice = sc.nextInt();
            
            switch(choice){
                case 1://view movie listings
                	MovieManager.getInstance().viewMovies("Customer");
                    break;
                case 2://view top 5
                	MovieManager.getInstance().viewTop5Cust();
                    break;

                case 3:
                	int subchoice;
                	
                	do {
                        System.out.println(	"=========== SEARCH BOOKING HISTORY =============\n" +
			                    "| 1. Check via email address					    		|\n" +
			                    "| 2. Check via mobile number 					    		|\n" +
				                "| 0. Back to CustomerApp......                             |\n" +
				                "============================================================");                    		
                		
                        System.out.println("Enter choice: ");
                        
                        if (!sc.hasNextInt()) {
                    		System.out.println("Invalid input type. Please choose a choice from 0-2.");
                    		sc.next(); // Remove newline character
                    		subchoice = -1;
                    		continue;
                    	}
                        
                        subchoice = sc.nextInt();
                        
	                	switch (subchoice) {
	                	case 1:
	                		CustomerManager.getInstance().printPastBookingByEmail();
	                		break;
	                	case 2:
	                		CustomerManager.getInstance().printPastBookingByMobile();
	                		break;
	                	case 0:
	                		System.out.println("Back to CustomerApp......");
	                		break;
                		default: 
                			System.out.println("Invalid choice. Please choose between 0-2.");
                			break;
	                	}
                	} while (subchoice!=0);
                	
                    break;
                case 0:
                	System.out.println("Back to MOBLIMA APP......");
                	break;
                default: 
                	System.out.println("Invalid choice. Please choose between 0-3.");
                	break;
	            }
	        } while (choice != 0);
		}
    }
	
