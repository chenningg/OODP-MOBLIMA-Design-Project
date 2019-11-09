import java.io.IOException;
import java.util.Scanner;

public class StaffApp {

    private static StaffApp single_instance = null;

    private StaffApp(){}

    public static StaffApp getInstance()
    {
        if (single_instance == null)
            single_instance = new StaffApp();
        return single_instance;
    }

    public boolean loginMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        int input;
        String username,password;
        boolean authenticate = false;

        System.out.println("==================== MOBLIMA STAFF APP ====================\n");
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

    public boolean staffMenu(){
        Scanner sc = new Scanner(System.in);
        int input;

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
                //View Top 5
                break;
            case 2:
                //System Settings
                break;
            case 3:
                //Movie Database
                break;
            case 4:
                //Showtimes Database
                break;
            case 5:
                new StaffManager().logout();
                break;
        }
        return true;
    }



    public static void main(String[] args) throws IOException {
        StaffApp sa = StaffApp.getInstance();
        boolean loggedIn = sa.loginMenu();
        while(loggedIn){
            loggedIn = sa.staffMenu();
        }
    }
}
