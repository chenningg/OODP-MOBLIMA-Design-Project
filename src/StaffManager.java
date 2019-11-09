import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StaffManager {

    public boolean login(int searchColumnIndex, String username, String password) throws IOException {
        String resultRow = null;
        BufferedReader br = new BufferedReader(new FileReader("test.csv"));
        String line;
        while((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values[searchColumnIndex].equals(username)) {
                if (values[searchColumnIndex + 1].equals(password)) {
                    return true;
                }
            }
        }
        br.close();
        return false;
    }
    public boolean logout(){
        return false;
    }
}



//Methods
//    private static final String COMMA_DELIMITER = ",";
//    private static final int username_index = 0;
//    private static final int password_index = 1;
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