import java.io.*;
import java.util.ArrayList;

public class Cineplex {
	// Attributes
	private String cineplexID;
	private String cineplexName;
	private ArrayList<Cinema> cinemas;
	
	
	// Constructor
	public Cineplex(String cineplexID) {
		this.cinemas = new ArrayList<Cinema>();
		this.setCineplexID(cineplexID);
		this.openCinemaFile(cineplexID);
	}


	// Getters
	public String getCineplexID() {return cineplexID;}
	public String getCineplexName() {return cineplexName;}
	public ArrayList<Cinema> getCinemas() {return cinemas;}


	// Setters
	public void setCineplexID(String cineplexID) {this.cineplexID = cineplexID;}
	public void setCineplexName(String cineplexName) {this.cineplexName = cineplexName;}
	
	public void addCinemas(String cinemaID) {
    	Cinema cinema = new Cinema(cinemaID);
    	cinemas.add(cinema); 
	}
	
	
	// Others
	
	
    // File Reader
    public void openCinemaFile(String cineplexID) {
		try {
			// current folder is \src
			FileReader frStream = new FileReader( "./data/cineplexes/cineplex_" + cineplexID + ".txt" );
			BufferedReader brStream = new BufferedReader( frStream );
			String inputLine;
			int i = 0;
			do {
				inputLine = brStream.readLine(); // read in a line
				if (inputLine == null) {break;} // end of file
				
				if (i==0) {
					// first line of file is the cineplex name
					this.setCineplexName(inputLine);
				}
				else {	
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
