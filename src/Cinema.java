import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Cinema {
	// Attributes
	private String cinemaID;
	private int hallNo;
	private CinemaType cinemaType;
	private int totalSeatNo;
	private int occupiedSeatsNo;
	private char[][] cinemaLayout;
	
	
	
	
	
	
	// Constructor	
	public Cinema(String cinemaID) {
		this.setCinemaID(cinemaID);	
		if (cinemaID.equals("CAT_001")) {
			this.openCinemaFile(cinemaID);
//			System.out.println(this.cinemaLayout);
		}
	}

	
	// Getters
	public String getCinemaID() {return cinemaID;}
	public int getHallNo() {return hallNo;}
	public CinemaType getCinemaType() {return cinemaType;}
	public int getTotalSeatNo() {return totalSeatNo;}
	public int getOccupiedSeatsNo() {return occupiedSeatsNo;}
	public char[][] getCinemaLayout() {return cinemaLayout;}
	

	
	// Setters
	public void setCinemaID(String cinmaID) {this.cinemaID = cinmaID;}
	public void setHallNo(int hallNo) {this.hallNo = hallNo;}
	public void setCinemaType(CinemaType cinemaType) {this.cinemaType = cinemaType;}
	public void setTotalSeatNo(int totalSeatNo) {this.totalSeatNo = totalSeatNo;}
	public void setOccupiedSeatsNo(int occupiedSeatsNo) {this.occupiedSeatsNo = occupiedSeatsNo;}

	public void setCinemaRows(int rowCount) {
		this.cinemaLayout = new char[rowCount][];
	}
	public void setCinemaColumns(int columnCount) {
		int i=0;
		while (this.cinemaLayout[i] != null) {
			this.cinemaLayout[i] = new char[columnCount];
			i++;
		}
	}
	public void setCinemaLayout(int layoutRow, String inputLine) {
		int i=0;
		System.out.println(layoutRow);
		while (i<inputLine.length()) {
//			this.cinemaLayout[layoutRow][i] = inputLine.charAt(i);
//			System.out.println(this.cinemaLayout[layoutRow]);
			System.out.println(this.cinemaLayout);
			i++;
		}
	}
	
	
    // File Reader
    public void openCinemaFile(String cinemaID) {
		try {
			// current folder is \src
			FileReader frStream = new FileReader( "..\\data\\cinemas\\cinema_" + cinemaID + ".txt" );
			BufferedReader brStream = new BufferedReader( frStream );
			String inputLine;
			int i = 0;

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
					case 4:
						// fifth line of file is the number of rows in the seat layout
						this.setCinemaRows(Integer.parseInt(inputLine));
						break;
					case 5:
						// sixth line of file is the number of columns in the seat layout
						this.setCinemaColumns(Integer.parseInt(inputLine));
						break;					
					default:
						// seventh line of file onwards will be the seat layout
						this.setCinemaLayout(i-6, inputLine);
						break;
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
