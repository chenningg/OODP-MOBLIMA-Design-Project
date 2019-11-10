import java.io.IOException;
import java.util.Scanner;

public class StaffApp {

    private static StaffApp single_instance = null;

    private StaffApp(){}

    //Create Singleton getInstance
    public static StaffApp getInstance()
    {
        if (single_instance == null)
            single_instance = new StaffApp();
        return single_instance;
    }

    //LoginMenu
    //boolean function - returns true when logged in else returns false
    public boolean loginMenu() throws IOException
    {
        Scanner sc = new Scanner(System.in);
        int input;
        String username,password;
        boolean authenticate = false;

        System.out.println("==================== MOBLIMA STAFF APP ====================");
        System.out.println("| 1. Login                                                |\n"+
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
                    authenticate = new StaffManager().login(0, username, password);
                    if (!authenticate) {
                        System.out.println("Invalid Username or Password, please try again.");
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

    public boolean staffMenu()
    {
        Scanner sc = new Scanner(System.in);
        int input;
        boolean loginStatus=true;

        System.out.println("==================== MOBLIMA STAFF APP ====================\n" +
                "| 1. View Top 5 Movies                                     |\n" +
                "| 2. Configure System Settings                             |\n" +
                "| 3. Movie Database                                        |\n" +
                "| 4. Showtime Database                                     |\n" +
                "| 5. Logout                                                |\n" +
                "===========================================================");

        System.out.println("Enter Choice: ");
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
                //Movie Database
                break;
            case 4:
                //Showtimes Database

                break;
            case 5:
                loginStatus=new StaffManager().logout();
                break;
        }
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
}
