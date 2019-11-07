import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cineplex {
	// Attributes
	private String cineplexID;
	private String cineplexName;
	private String address;
	private List<Cinema> cinemas;
	
	
	// Constructor
	public Cineplex(String cineplexName) {
		this.cinemas = new ArrayList<Cinema>();
		this.openCinemaFile(cineplexName);
	}



	// Getters
	public String getCineplexID() {return cineplexID;}
	public String getCineplexName() {return cineplexName;}
	public String getAddress() {return address;}
	public List<Cinema> getCinemas() {return cinemas;}


	
	// Setters
	public void setCineplexID(String cineplexID) {this.cineplexID = cineplexID;}
	public void setCineplexName(String cineplexName) {this.cineplexName = cineplexName;}
	public void setAddress(String address) {this.address = address;}
	
	public void addCinemas(String cinemaID) {
    	Cinema cinema = new Cinema(cinemaID);
    	cinemas.add(cinema); 
	}
	
	
    // File Reader
    public void openCinemaFile(String cineplexName) {
		try {
			// current folder is \src
			FileReader frStream = new FileReader( "..\\data\\cineplexes\\cineplex_" + cineplexName + ".txt" );
			BufferedReader brStream = new BufferedReader( frStream );
			String inputLine;
			int i = 0;

			do {
				inputLine = brStream.readLine(); // read in a line
				if (inputLine == null) {break;} // end of file
				
				if (i==0) {
					// first line of file is the cineplex name
					this.setCineplexName(inputLine);
				} else {	
					// all other lines are lists of cinemas the cineplex has
					this.addCinemas(inputLine);
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
