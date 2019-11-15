
import java.util.Scanner;

public class StaffApp {
	// Attributes
    private static StaffApp single_instance = null;
    
    private Scanner sc = new Scanner(System.in);

    private StaffApp(){}

    public static StaffApp getInstance()
    {
        if (single_instance == null)
            single_instance = new StaffApp();
        return single_instance;
    }

    
    // Public exposed methods to app
    public void displayLoginMenu() {
    	boolean loggedIn = false;
    	boolean quit = false;
		int choice;
		
		do {
	        System.out.println(	"==================== MOBLIMA STAFF APP ====================\n"+
				                "| 1. Login                                                |\n"+
				                "| 0. Back to MOBLIMA APP                                  |\n"+
				                "===========================================================");
	        System.out.println("Enter choice: ");
			choice = sc.nextInt();
			
			switch (choice) {
			case 1: 
                System.out.println("Username: ");
                String username = sc.next();
                System.out.println("Password: ");
                String password = sc.next();
                
                boolean authenticate = StaffManager.getInstance().login(username, password);
                
                
                if (authenticate) {
                	loggedIn = true;
                	this.displayLoggedInMenu();
                	quit = true;
                } else {
                	System.out.println("Invalid Username or Password, please try again.");
                }
				break;
			case 0:
				System.out.println("Back to MOBLIMA APP......");
				quit = true;
				break;
			}
		} while (loggedIn == false && quit == false);
    }
    
    
    // Private methods
    private void displayLoggedInMenu() {
		int choice;
		
		do {
            System.out.println(	"==================== MOBLIMA STAFF APP ====================\n" +
			                    "| 1. View Top 5 Movies                                     |\n" +
			                    "| 2. Configure System Settings                             |\n" +
			                    "| 3. Movie Database                                        |\n" +
			                    "| 0. Logout from StaffApp                                  |\n" +
			                    "===========================================================");
            System.out.println("Enter choice: ");
			choice = sc.nextInt();
			
			switch (choice) {
			case 1: 
                MovieManager.getInstance().viewTop5Staff();
				break;
			case 2:
				SystemSettingsManager.getInstance().displayMenu();
				break;
			case 3:
				MovieManager.getInstance().movieMenuStaff();
				break;
			case 0:
				System.out.println("Logging out from StaffApp......");
				break;
			default:
				System.out.println("Invalid choice. Please choose between 0-3.");
				break;
			}
		} while (choice != 0);
    }
    

    
    
    
    
   /* Already replicated the below functionality
    
    
    
    
    // Displays the Staff Login Menu returns true when logged in
    //LoginMenu
    //boolean function - returns true when logged in else returns false
    public boolean loginMenu() throws IOException
    {
        Scanner sc = new Scanner(System.in);
        int input;
        String username,password;
        boolean authenticate = false;

        System.out.println("==================== MOBLIMA STAFF APP ====================\n"+
                "| 1. Login                                                |\n"+
                "| 2. Exit                                                 |\n"+
                "===========================================================");

        System.out.println("Enter Choice: ");
        input = sc.nextInt();
        switch(input){
            case 1:
                while(!authenticate){
                    System.out.println("Username: ");
                    username = sc.next();
                    System.out.println("Password: ");
                    password = sc.next();
                    authenticate = StaffManager.getInstance().login(username, password);
                    if (!authenticate) {
                        System.out.println("Invalid Username or Password, please try again.");
                    } else {
                    	
                    }
                }
                break;
            case 2:
                break;
        }
        return authenticate;
    }

    //StaffMenu
    //Run this menu only when LoginMenu returns true
    //boolean function - returns true, logout returns false

    //Displays the Staff Menu after logging in.
    public boolean staffMenu()
    {
        Scanner sc = new Scanner(System.in);
        int input;
        boolean loginStatus=true;



        System.out.println("Enter Choice: ");

        do{
            System.out.println("==================== MOBLIMA STAFF APP ====================\n" +
                    "| 1. View Top 5 Movies                                     |\n" +
                    "| 2. Configure System Settings                             |\n" +
                    "| 3. Movie Database                                        |\n" +
                    "| 4. Showtime Database                                     |\n" +
                    "| 5. Logout                                                |\n" +
                    "===========================================================");
            input = sc.nextInt();
            switch(input){
                case 1:
                    MovieManager mm = MovieManager.getInstance();
                    mm.viewTop5Staff();
                    break;
                case 2:
                    SystemSettingsManager.getInstance().displayMenu();
                    break;
                case 3:
                    MovieManager.getInstance().movieMenu();
                    break;
                case 4:
                    ShowtimeManager.getInstance().showtimeMenu();
                    break;
                case 5:
                    loginStatus=new StaffManager().logout();
                    break;
            }
        }while(input != 5);

        return loginStatus;
    }



    public static void main(String[] args) throws IOException
    {
        StaffApp sa = StaffApp.getInstance();
        boolean loggedOut;
        boolean loggedIn= true;
        while(loggedIn != false){
            loggedIn= sa.loginMenu();
            if(loggedIn){
                loggedOut=sa.staffMenu();
                if(loggedOut == false)
                    continue;
            }
        }
    }
   */
}
