import java.util.Scanner;

public class CustomerApp {
	// Attributes
    private static CustomerApp single_instance = null;
    
    private Scanner sc = new Scanner(System.in);

    CustomerApp(){}

    public static CustomerApp getInstance()
    {
        if (single_instance == null)
            single_instance = new CustomerApp();
        return single_instance;
    }
	
    public void displayCustomerMenu() {
		int choice;
    	
		
        //Instantiate Objects
        MovieManager mm = MovieManager.getInstance();
        ReviewManager rm= ReviewManager.getInstance();
        CustomerManager cm= CustomerManager.getInstance();


        
        
    	////////////////////////////////////////////////////////////////////////////////////////////
        
        // Still editing
        // Must pass control over to other managers
        // Do not keep the checking within here, only keep the logic
        // How should we handle scanners? Pass it on and on or create in each class
        
        ////////////////////////////////////////////////////////////////////////////////////////////
		
		
		do {
	    	System.out.println("================= MOBLIMA CUSTOMER APP =================\n" +
	                "1. View movie listings \n" +
	                "2. Leave review\n" +
	                "3. View top 5\n" +
	                "4. Check booking history\n" +
	                "0. Quit\n");
	    	
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            
            switch(choice){
                case 1://view movie listings
                	mm.displayMovies();
                    break;

                case 2://leave review
                	String searchterm;
                	int searchSize;
                	
                    System.out.println("Enter search term: ");
                    searchterm = sc.next();
                    
                    searchSize = MovieManager.getInstance().searchMovies(searchterm).size();
                    
                    //search for movie using searchterm
                    if(searchSize == 0){
                        System.out.println("No Movies found.");
                        break;
                    }
                    
                    //print out movies containing searchterm
                    for(int i=0;i<searchSize;i++){
                        System.out.println(i+1+". "+mm.searchMovies(searchterm).get(i));
                    }
                    
                    //let user choose movie
                    do{
                        System.out.println("Enter choice of movie(1-" + searchSize+": ");
                        choice = sc.nextInt();

                    }while(choice <1 || choice>searchSize);
                    rm.createReview(mm.searchMovies(searchterm).get(choice-1));
                    break;

                case 3://view top 5
                	mm.viewTop5Cust();
                    break;

                case 4://check booking history
                	int emailOrMobile;
                	
                	do {
	                	System.out.print("Enter 1,2 or 3: \n"
	                			+ "1. Check via email address\n"
	                			+ "2. Check via mobile number\n"
	                			+ "0. Back to CustomerApp");
	                	emailOrMobile= sc.nextInt();
	                	switch (emailOrMobile) {
	                	case 1:
	                		cm.printPastBookingByEmail();
	                		break;
	                	case 2:
	                		cm.printPastBookingByMobile();
	                		break;
	                	case 0:
	                		System.out.println("Back to CustomerApp......");
	                		break;
                		default: 
                			System.out.println("Invalid choice. Please choose between 0-2.");
                			break;
	                	}
                	}while(emailOrMobile!=0);
                	
                    break;
                case 0:
                	System.out.println("Back to MOBLIMA APP......");
                	break;
                default: 
                	System.out.println("Invalid choice. Please choose between 0-4.");
                	break;
	            }
	        } while(choice != 0);
		}
    }
	
