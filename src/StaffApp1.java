
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class StaffApp1 {



    public static int loginMenu(){
        Scanner sc = new Scanner(System.in);
        int input;

        System.out.println("==================== MOBLIMA STAFF APP ====================\n");
        System.out.println("1. Login\n"+
                           "2. Exit");

        System.out.println("Enter Choice: ");
        input = sc.nextInt();
        return input;
    }

    public static int displayMenu(){
        Scanner sc = new Scanner(System.in);
        int input;

        System.out.println("==================== MOBLIMA STAFF APP ====================\n" +
                           "| 1. View Top 5 Movies                                    |\n" +
                           "| 2. Configure System Settings                            |\n" +
                           "| 3. Movie Database                                       |\n" +
                           "| 4. Showtime Database                                   |\n" +
                           "| 5. Logout                                               |\n" +
                           "===========================================================");

        System.out.println("Enter Choice: ");
        input = sc.nextInt();
        return input;
    }


    public static void main(String[] args) throws IOException {
        int userChoice=0;
        String username,password;
        boolean authenticate = false;
        Scanner sc = new Scanner(System.in);
        while(!authenticate && (userChoice != 1 || userChoice != 2)) {
            userChoice = loginMenu();
            switch(userChoice){
                case 1:
                    System.out.println("Username: ");
                    username = sc.next();
                    System.out.println("Password: ");
                    password = sc.next();
                    authenticate = new StaffManager().login(0, username, password);
                    if (authenticate == false) {
                        System.out.println("Invalid Username or Password, please try again.");
                    }
                    break;
                case 2:
                    break;
            }
        }
        if(authenticate) {
            while(authenticate) {
                userChoice = displayMenu();
                switch (userChoice) {
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
                        authenticate = new StaffManager().logout();
                        break;
                }
            }
        }
        userChoice = loginMenu();

    }
}

