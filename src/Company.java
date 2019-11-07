import java.io.*;
import java.util.ArrayList;

public class Company {
	// Attributes
    private String companyName;
    private ArrayList<Cineplex> cineplexes;

    
    // Constructor
    Company(){
    	this.cineplexes = new ArrayList<Cineplex>();
    	this.openCompanyFile();
    }

    
    // Getters
    public String getCompanyName(){return this.companyName;}
    public ArrayList<Cineplex> getCineplexes(){return this.cineplexes;}


    // Setters
    public void setCompanyName(String companyName){
		this.companyName = companyName;
    }
    
    public void addCineplexes(String cineplexName){
    	Cineplex cineplex = new Cineplex(cineplexName);
    	cineplexes.add(cineplex); 
    }
    
    
    // Others
    
    
    // File Reader
    public void openCompanyFile() {
		try {
			// current folder is \src
			FileReader frStream = new FileReader( "..\\data\\company.txt" );
			BufferedReader brStream = new BufferedReader( frStream );
			String inputLine;
			int i = 0;

			do {
				inputLine = brStream.readLine(); // read in a line
				if (inputLine == null) {break;} // end of file
				
				if (i==0) {
					// first line of file is the company name
					this.setCompanyName(inputLine);
				} else {	
					// all other lines are lists of cineplexes the company owns
					this.addCineplexes(inputLine);
				}

				i++;
			} while (inputLine != null);
			
			brStream.close();	
			
		} catch ( FileNotFoundException e ) {
			System.out.println( "Error opening the input file!" + e.getMessage() );
			System.exit( 0 );
		} catch ( IOException e ) {
			System.out.println( "IO Error!" + e.getMessage() );
			e.printStackTrace();
			System.exit( 0 );
		}           

    }
}


// Driver app for testing
class CompanyApp {
	public static void main(String[] args) {
		Company myCompany = new Company();
		System.out.println(myCompany.getCompanyName());
		System.out.println(myCompany.getCineplexes());
		System.out.println(myCompany.getCineplexes().get(0).getCinemas());
		System.out.println(myCompany.getCineplexes().get(0).getCinemas().get(0).getCinemaLayout());
		
	}
}