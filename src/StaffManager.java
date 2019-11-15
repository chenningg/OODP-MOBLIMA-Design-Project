import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StaffManager {
	// Attributes
    private static StaffManager single_instance = null;

    private StaffManager(){}

    public static StaffManager getInstance()
    {
        if (single_instance == null)
            single_instance = new StaffManager();
        return single_instance;
    }	
	
	
    public boolean login(String username, String password) {
        try {
	    	String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/accounts/staff_accounts.csv";
	        BufferedReader br = new BufferedReader(new FileReader(filepath));
	        String line;
	        while((line = br.readLine()) != null) {
	            String[] values = line.split(",");
	            if (values[0].equals(username)) {
	                if (values[1].equals(password)) {
	                	br.close();
	                    return true;
	                }
	            }
	        }
	        br.close();
	        return false;
        } catch ( FileNotFoundException e ) {
			System.out.println( "Error opening the input file!" + e.getMessage() );
			System.exit( 0 );
		} catch ( IOException e ) {
			System.out.println( "IO Error!" + e.getMessage() );
			e.printStackTrace();
			System.exit( 0 );
		}	
        
        return false;
    }
    
    
    public boolean logout(){
        return false;
    }
}



