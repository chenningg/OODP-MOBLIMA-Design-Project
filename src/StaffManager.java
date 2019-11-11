import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StaffManager {
    //TO DO add Project Root Path Finder to read CSV
    public boolean login(int searchColumnIndex, String username, String password) throws IOException {
        String resultRow = null;
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/intialisation/staffAccounts/staffaccounts.csv";
        BufferedReader br = new BufferedReader(new FileReader(filepath));
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



