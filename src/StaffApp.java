import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class StaffApp {

    //Attributes
    private boolean loggedIn;

    private StaffAccount user = null;

    //Methods
    private static final String COMMA_DELIMITER = ",";
    private static final int username_index = 0;
    private static final int password_index = 1;
//    public static void readCsvFile(String fileName){
//        BufferedReader fileReader = null;
//        try{
//            List accounts = new ArrayList();
//            String line = "";
//            //Create file reader
//            fileReader = new BufferedReader(new FileReader(fileName));
//            //Read the CSV file header to skip it
//            fileReader.readLine();
//            //Read the file line by line starting from the 2nd line
//            while((line = fileReader.readLine()) != null){
//                String[] tokens = line.split(COMMA_DELIMITER);
//                if(tokens.length > 0){
//                    //Create new object and fill data
//                    StaffAccount account = new StaffAccount(tokens[username_index],tokens[password_index]);
//                    accounts.add(account);
//                }
//            }
//            //Print new account list
//            for(int i =0;i<accounts.size();i++){
//                System.out.println(accounts.get(i).toString());
//            }
//        }
//        catch (Exception e) {
//            System.out.println("Error in CsvFileReader !!!");
//            e.printStackTrace();
//        } finally {
//            try {
//                fileReader.close();
//            } catch (IOException e) {
//                System.out.println("Error while closing fileReader !!!");
//                e.printStackTrace();
//            }
//        }
//    }

    public boolean login(int searchColumnIndex, String username, String password) throws IOException{
        String resultRow = null;
        BufferedReader br = new BufferedReader(new FileReader("test.csv"));
        String line;
        while((line = br.readLine()) != null){
            String[] values = line.split(",");
            if(values[searchColumnIndex].equals(username)){
                if(values[searchColumnIndex+1].equals(password)){
                    return true;
                }
        }
        br.close();
        return false;
    }



    public void logout(){};
    //Getters
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public StaffAccount getUser() {
        return user;
    }
    //Setters
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    public void setUser(StaffAccount user) {
        this.user = user;
    }



    public static void main(String[] args) {
        // App Interface

        //Instantiate Objects
        SystemSettingsManager ss = SystemSettingsManager.getInstance();
        MovieManager mm = MovieManager.getInstance();
        Scanner sc = new Scanner(System.in);
        int login,input;
        String username,password;

        System.out.println("==================== MOBLIMA STAFF APP ====================\n");
        System.out.println("1. Login");
        do{
            System.out.println("Enter number 1 to login: ");
            login = sc.nextInt();
        }while(login!=1);
        System.out.println("Username: ");
        username = sc.next();
        System.out.println("Password: ");
        password = sc.next();


        System.out.println("==================== MOBLIMA STAFF APP ====================\n" +
                           "| 1. Configure System Settings                            |\n" +
                           "| 2. Add Movie                                            |\n" +
                           "| 3. Update Movie Details                                 |\n" +
                           "| 4. Delete Movie                                         |\n" +
                           "| 5. List Top 5 Movies by Ticket Sales                    |\n" +
                           "| 6. List Top 5 Movies by Review Score                    |\n" +
                           "| 7. Exit App                                             |\n"+
                           "===========================================================");



        do {
            System.out.println("Enter the number of your choice(1-8): ");
            input = sc.nextInt();
            switch (input) {
                case 1:
                    ss.displayMenu();
                    break;
                case 2:
                    mm.addMovie();
                    mm.peekMovie();
                    break;
                case 3:
                    mm.editMovie();
                    break;
                case 4:
                    mm.deleteMovie();
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        } while (input != 7);
    }
}

