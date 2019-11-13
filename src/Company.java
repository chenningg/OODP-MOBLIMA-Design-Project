import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Company implements Serializable {
	// Attributes
    private String companyName;
    private List<Cineplex> cineplexes;
    private List<String> cineplexNames;
    
    
    // Constructor
    Company(){
    	this.cineplexes = new ArrayList<Cineplex>();
    	this.cineplexNames = new ArrayList<String>();
    	this.openCompanyFile();
    }

    
    // Getters
    public String getCompanyName() {return this.companyName;}
    public List<Cineplex> getCineplexes() {return this.cineplexes;}
    public List<String> getCineplexNames() {return this.cineplexNames;} 


    // Setters
    public void setCompanyName(String companyName){
		this.companyName = companyName;
    }
    
    public void addCineplexes(String cineplexName){
    	Cineplex cineplex = new Cineplex(cineplexName);
    	this.cineplexes.add(cineplex); 
    	this.cineplexNames.add(cineplex.getCineplexName());
    }
    
    
    
    
    
    
    
    
    
    
    
    
	// Initializers: Below code used only for the very first run of the app
    // File Reader
    public void openCompanyFile() {
		try {
			// Get filepath
			String filePath = ProjectRootPathFinder.findProjectRootPath();
			
			if (filePath == null) {
				throw new IOException("Cannot find root");
			} else {
				filePath = filePath + "/data/company/company.txt";
			}
			
			// Open file and traverse it
			FileReader frStream = new FileReader( filePath );
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