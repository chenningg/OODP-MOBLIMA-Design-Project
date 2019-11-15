import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class DataInitialiser {
		
	// Loads all text files in the specified folder and returns the list of files
	public File[] getAllFiles(String folderPath) {
		
		// Finds folder and gets a list of all files in folder
		File directory = new File(folderPath);
		return(directory.listFiles());
	}
	
	
	// Loads movie data
	public List<Movie> initialiseMovieData(String basePath) {
		
		String folderPath = basePath + "/movies";
		File[] files = getAllFiles(folderPath);
		
		List<Movie> movies = new ArrayList<Movie>();
		
		// Go through each file and load them into actual data storage path
		for (int i = 0; i < files.length; i++)
		{
			String filePath = files[i].getPath();
			
			try {
			
				// Open file and traverse it						
				FileReader frStream = new FileReader( filePath );
				BufferedReader brStream = new BufferedReader( frStream );
				String inputLine;
				
				Movie newMovie = new Movie();
				
				// Movie ID
				inputLine = brStream.readLine();
				newMovie.setMovieID(IDHelper.getLatestID("movie"));
				
				// Movie title
				inputLine = brStream.readLine();
				newMovie.setTitle(inputLine);
				
				// Genres
				inputLine = brStream.readLine();
				ArrayList<String> genres = new ArrayList<String>(Arrays.asList(inputLine.split(",")));
				
				ArrayList<Genre> movieGenres = new ArrayList<Genre>();
				
				for (int k = 0; k < genres.size(); k++) {
					movieGenres.add(Genre.valueOf(genres.get(k)));
				}
				
				newMovie.setGenres(movieGenres);
				
				// Director
				inputLine = brStream.readLine();
				newMovie.setDirector(inputLine);
				
				// Cast
				inputLine = brStream.readLine();
				ArrayList<String> cast = new ArrayList<String>(Arrays.asList(inputLine.split(",")));
				newMovie.setCast(cast);
				
				// Synopsis
				inputLine = brStream.readLine();
				newMovie.setSynopsis(inputLine);
				
				// Movie rating
				inputLine = brStream.readLine();
				newMovie.setMovieRating(MovieRating.valueOf(inputLine));
				
				// Movie format
				inputLine = brStream.readLine();
				ArrayList<String> formats = new ArrayList<String>(Arrays.asList(inputLine.split(",")));
				ArrayList<MovieFormat> movieFormats = new ArrayList<MovieFormat>();
				
				for (int j = 0; j < formats.size(); j++) {
					movieFormats.add(MovieFormat.valueOf(formats.get(j)));
				}
				
				newMovie.setMovieFormats(movieFormats);
				
				// Movie duration
				inputLine = brStream.readLine();
				newMovie.setMovieDuration(Integer.valueOf(inputLine));
				
				// Movie status
				inputLine = brStream.readLine();
				newMovie.setShowingStatus(ShowingStatus.valueOf(inputLine));
				
				// Release date
				inputLine = brStream.readLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
				//convert String to LocalDate
				LocalDate releaseDate = LocalDate.parse(inputLine, formatter);
				newMovie.setReleaseDate(releaseDate);
				
				brStream.close(); // Close file
				
				// Now that we have a new movie, we add it to our movies array and initialize it.
				movies.add(newMovie);
				
				// Finally, serialize the movie file
				String storagePath =  ProjectRootPathFinder.findProjectRootPath() + "/data/movies/movie_" + newMovie.getMovieID() + ".dat";
				
				SerializerHelper.serializeObject(newMovie, storagePath);
				
			} catch ( FileNotFoundException e ) {
				System.out.println( "File not found!" + e.getMessage() );
				System.exit( 0 );
			} catch ( IOException e ) {
				System.out.println( "IO Error!" + e.getMessage() );
				e.printStackTrace();
				System.exit( 0 );
			}
		}
		
		// Return a reference to movies for showtime use
		return movies;
	}
	
	
	// Loads showtime data
	public void initialiseShowtimeData(List<Movie> movies, String basePath) {
		
		String folderPath = basePath + "/showtimes";
		File[] files = getAllFiles(folderPath);
		Queue<Cinema> cinemaInstances = new LinkedList<>(); // If not null we add it to next showtime added
		
		// Load any cinemas that are premade for showtime (Map cinema to showtime on first come first served basis)
		String cinemaPath = basePath + "/cinemas";
		File[] cinemas = getAllFiles(cinemaPath);
		
		for (File cinemaFile : cinemas) {
			cinemaInstances.add(initialiseCinemaData("AAA", cinemaFile.getPath()));
		}
		
		// Go through each file and load them into actual data storage path
		for (int i = 0; i < files.length; i++)
		{
			String filePath = files[i].getPath();	
			
			try {				
				// Open file and traverse it				
				FileReader frStream = new FileReader( filePath );
				BufferedReader brStream = new BufferedReader( frStream );
				String inputLine;
				
				Showtime newShowtime = new Showtime();
				
				// Showtime ID
				inputLine = brStream.readLine();
				newShowtime.setShowtimeID(IDHelper.getLatestID("showtime"));
				
				// Showtime date and time
				inputLine = brStream.readLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				newShowtime.setDateTime(LocalDateTime.parse(inputLine, formatter));
				
				// Showtime movie_id
				inputLine = brStream.readLine(); // Movie name
				for (Movie movie : movies) {
					if (movie.getTitle() == inputLine) {
						newShowtime.setMovieID(movie.getMovieID());
						break;
					}
				}
				
				// Showtime movie format
				inputLine = brStream.readLine();
				newShowtime.setMovieFormat(MovieFormat.valueOf(inputLine));
				
				// Showtime cinema instance
				// If there are existing cinemas, map them to current showtime first. (filled cinema)
				// If no existing cinemas to map, then initialize from base cinema file (empty cinema)
				inputLine = brStream.readLine(); // CinemaID
				
				if (cinemaInstances.size() > 0) { // Existing cinemas exist
					newShowtime.setCinema(cinemaInstances.poll()); // Retrieves first cinema in queue
				}
				else { // No existing cinemas, we make new base cinema from cinema base files (cinema_ID, filepath)
					String cinemaFilePath = ProjectRootPathFinder.findProjectRootPath() + "/data/cinemas/cinema_" + inputLine + ".txt";
					newShowtime.setCinema(initialiseCinemaData(inputLine, cinemaFilePath));
				}
				
				// Showtime cineplex name
				inputLine = brStream.readLine();
				newShowtime.setCineplexName(inputLine);
				
				// Update showtime status
				newShowtime.updateCinemaStatus();
				
				brStream.close(); // Close file				
				
				// Serialize showtime file
				String storagePath =  ProjectRootPathFinder.findProjectRootPath() + "/data/showtimes/showtime_" + newShowtime.getShowtimeID() + ".dat";
				
				SerializerHelper.serializeObject(newShowtime, storagePath);
			    
			} catch ( FileNotFoundException e ) {
				System.out.println( "File not found!" + e.getMessage() );
				System.exit( 0 );
			} catch ( IOException e ) {
				System.out.println( "IO Error!" + e.getMessage() );
				e.printStackTrace();
				System.exit( 0 );
			}
		}
	}
	
	
	public Cinema initialiseCinemaData(String cinemaID, String filePath) {
		Cinema newCinema = new Cinema(cinemaID);
		
		try {			
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
						newCinema.setHallNo(Integer.parseInt(inputLine));
						break;		
					case 1:
						// second line of file is the cinema type (ENUM)
						newCinema.setCinemaType(CinemaType.valueOf(inputLine));
						break;					
					case 2: 
						// third line of file is the total seats
						newCinema.setTotalSeatNo(Integer.parseInt(inputLine));
						break;	
					case 3:
						// fourth line of file is the occupied seats
						newCinema.setOccupiedSeatsNo(Integer.parseInt(inputLine));
						break;				
					default:
						// fifth line of file onwards will be the cinema layout
						seatingPlan.add(inputLine);
						break;
				}
				i++;
			} while (inputLine != null);
			
			brStream.close();	
			
			newCinema.setCinemaLayout(seatingPlan);
			
			return newCinema;
			
		} catch ( FileNotFoundException e ) {
			System.out.println( "Error opening the input file!" + e.getMessage() );
			System.exit( 0 );
		} catch ( IOException e ) {
			System.out.println( "IO Error!" + e.getMessage() );
			e.printStackTrace();
			System.exit( 0 );
		}         
		
		return newCinema;
    }	
}

class Main {
	public static void main(String[] args) {
			
		// Get project root
		String initialisationFolderPath = ProjectRootPathFinder.findProjectRootPath() + "/data/initialisation";
		
		// Initialise data
		DataInitialiser dataInitialiser = new DataInitialiser();
		
		// Movies
		List<Movie> movieList = dataInitialiser.initialiseMovieData(initialisationFolderPath);
		
		// Showtimes initialisation, store MOVIE ID (NOT INSTANCE) of movie and a new INSTANCE of cinema
		dataInitialiser.initialiseShowtimeData(movieList, initialisationFolderPath);
	}
}
