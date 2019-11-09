import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cinema {
	// Attributes
	private String cinemaID;
	private int hallNo;
	private CinemaType cinemaType;
	private int totalSeatNo;
	private int occupiedSeatsNo;
	private List<String> cinemaLayout;
	
	
	// Constructor	
	public Cinema(String cinemaID) {
		this.cinemaLayout = new ArrayList<String>();
		this.setCinemaID(cinemaID);			
		this.openCinemaFile(cinemaID);
	}

	
	// Getters
	public String getCinemaID() {return cinemaID;}
	public int getHallNo() {return hallNo;}
	public CinemaType getCinemaType() {return cinemaType;}
	public int getTotalSeatNo() {return totalSeatNo;}
	public int getOccupiedSeatsNo() {return occupiedSeatsNo;}
	public List<String> getCinemaLayout() {return cinemaLayout;}
	
	public void printCinemaLayout() {
		int i=0;
		while (i<this.getCinemaLayout().size()) {
			System.out.println(this.getCinemaLayout().get(i));
			i++;
		}
	}

	
	// Setters
	public void setCinemaID(String cinmaID) {this.cinemaID = cinmaID;}
	public void setHallNo(int hallNo) {this.hallNo = hallNo;}
	public void setCinemaType(CinemaType cinemaType) {this.cinemaType = cinemaType;}
	public void setTotalSeatNo(int totalSeatNo) {this.totalSeatNo = totalSeatNo;}
	public void setOccupiedSeatsNo(int occupiedSeatsNo) {this.occupiedSeatsNo = occupiedSeatsNo;}
	public void setCinemaLayout(List<String> list) {this.cinemaLayout = list;}
	
	
	// Others
	public void updateOccupiedSeatsNo(int seatsBooked) {
		if (this.totalSeatNo - (this.occupiedSeatsNo+seatsBooked) < 0) {
			System.out.println("Not enough seats left in the cinema");
		} else {
			this.occupiedSeatsNo+=seatsBooked;
		}
	}
	
	
    // File Reader
    public void openCinemaFile(String cinemaID) {
		try {
			// Get filepath
			String filePath = ProjectRootPathFinder.findProjectRootPath();
			
			if (filePath == null) {
				throw new IOException("Cannot find root");
			} else {
				filePath = filePath + "/data/cinemas/cinema_" + cinemaID + ".txt";
			}			
			
			// Open file and traverse it						
			FileReader frStream = new FileReader( filePath );
			BufferedReader brStream = new BufferedReader( frStream );
			String inputLine;
			
			int i = 0;
			ArrayList<String> seatingPlan = new ArrayList<String>();

			do {
				inputLine = brStream.readLine(); // read in a line
				if (inputLine == null) {break;} // end of file
				
				switch (i) {
					case 0:
						// first line of file is the hall number
						this.setHallNo(Integer.parseInt(inputLine));
						break;		
					case 1:
						// second line of file is the cinema type (ENUM)
						this.setCinemaType(CinemaType.valueOf(inputLine));
						break;					
					case 2: 
						// third line of file is the total seats
						this.setTotalSeatNo(Integer.parseInt(inputLine));
						break;	
					case 3:
						// fourth line of file is the occupied seats
						this.setOccupiedSeatsNo(Integer.parseInt(inputLine));
						break;				
					default:
						// fifth line of file onwards will be the cinema layout
						seatingPlan.add(inputLine);
						break;
				}
				i++;
			} while (inputLine != null);
			
			brStream.close();	
			
			this.setCinemaLayout(seatingPlan);
			
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
