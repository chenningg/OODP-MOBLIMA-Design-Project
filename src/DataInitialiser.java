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
	
	
	// Initialize movie data
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
				
				// Set showtime IDs list to be empty at first
				newMovie.setShowtimeIDs(new ArrayList<String>());
				
				brStream.close(); // Close file
				
				// Now that we have a new movie, we add it to our movies array and initialize it.
				movies.add(newMovie);
				
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
	
	
	// Initialize showtime data
	public List<Movie> initialiseShowtimeData(List<Movie> movies, String basePath) {
		
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
				
				// Showtime movie_id. Add it to the movie list of showtimes too.
				inputLine = brStream.readLine(); // Movie name
				for (Movie movie : movies) {
					if (movie.getTitle().equals(inputLine)) {
						newShowtime.setMovieID(movie.getMovieID());
						movie.addShowtimeID(newShowtime.getShowtimeID());
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

		return movies;
	}
	
	
	// Initialize review data
	public List<Movie> initialiseReviewData(List<Movie> movies, String basePath) {
		
		String folderPath = basePath + "/reviews";
		File[] files = getAllFiles(folderPath);

		// Go through each file and load them into actual data storage path
		for (int i = 0; i < files.length; i++)
		{
			String filePath = files[i].getPath();	
			
			try {				
				// Open file and traverse it				
				FileReader frStream = new FileReader( filePath );
				BufferedReader brStream = new BufferedReader( frStream );
				String inputLine;
				
				Review newReview = new Review();
				
				// Review ID
				newReview.setReviewID(IDHelper.getLatestID("review"));
				
				
				// Review movie_id. Add it to the movie list of showtimes too.
				inputLine = brStream.readLine(); // Movie name
				for (Movie movie : movies) {
					if (movie.getTitle().equals(inputLine)) {
						newReview.setMovieID(movie.getMovieID());
						break;
					}
				}
				
				// Reviewer name
				inputLine = brStream.readLine();
				newReview.setReviewerName(inputLine);
				
				// Review Title
				inputLine = brStream.readLine();
				newReview.setReviewTitle(inputLine);				
				
				// Review 
				inputLine = brStream.readLine();
				newReview.setReviewBody(inputLine);
				
				// Review score
				inputLine = brStream.readLine();
				newReview.setScore(Double.parseDouble(inputLine));
				
				// Review Datetime
				inputLine = brStream.readLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");	
				newReview.setReviewDateTime(LocalDateTime.parse(inputLine, formatter));			
				
				for (Movie movie : movies) {
					if (movie.getMovieID().equals(newReview.getMovieID())) {
						
						movie.setTotalReviewNo(movie.getTotalReviewNo()+1);
				        movie.setTotalReviewScore(movie.getTotalReviewScore()+newReview.getScore());
				        movie.addMovieReview(newReview.getReviewID());
				        movie.setAverageReviewScore(movie.getTotalReviewScore()/movie.getTotalReviewNo());						
						
						newReview.setMovieID(movie.getMovieID());
						movie.addMovieReview(newReview.getReviewID());
						break;
					}
				}
				


				
				
		        
				brStream.close(); // Close file				
				
				// Serialize showtime file
				String storagePath =  ProjectRootPathFinder.findProjectRootPath() + "/data/reviews/review_" + newReview.getReviewID() + ".dat";
				
				SerializerHelper.serializeObject(newReview, storagePath);
			    
			} catch ( FileNotFoundException e ) {
				System.out.println( "File not found!" + e.getMessage() );
				System.exit( 0 );
			} catch ( IOException e ) {
				System.out.println( "IO Error!" + e.getMessage() );
				e.printStackTrace();
				System.exit( 0 );
			}
		}
		
		return movies;
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

	
	public void resetAllData() {
		// Initialise data		
		this.resetFolders("movies");
		this.resetFolders("showtimes");
		this.resetFolders("reviews");
		this.resetFolders("transactions");
		this.resetFolders("bookings");
		this.resetID("movie_id.txt");
		this.resetID("showtime_id.txt");
		this.resetID("review_id.txt");
		this.resetID("booking_id.txt");
		
		System.out.println("All data reset");
	}

	
	public void resetFolders(String folderName) {
		String root = ProjectRootPathFinder.findProjectRootPath();
		
		File dir = new File(root + "/data/" + folderName);
		
		for(File file: dir.listFiles()) 
		    if (!file.isDirectory()) 
		        file.delete();
	}
	

	public void resetID(String fileName) {
		BufferedWriter bw = null;
		String root = ProjectRootPathFinder.findProjectRootPath();
		root = root + "/data/ids/" + fileName;
		
		try {
	         // Specify the file name and path here
			 File file = new File(root);
	
			 // This logic will make sure that the file gets created if it is not present at the specified location
			 if (!file.exists()) {
			    file.createNewFile();
			 }
	
			 FileWriter fw = new FileWriter(file);
			 bw = new BufferedWriter(fw);
			 bw.write("");
	
        } catch (IOException ioe) {
        	 ioe.printStackTrace();
		} finally { 
			   try {
			      if(bw!=null)
				 bw.close();
			   } catch(Exception ex){
			       System.out.println("Error in closing the BufferedWriter"+ex);
			   }
		}
	}
}

class Main {
	public static void main(String[] args) {

		// Data initializer 
		DataInitialiser dataInitialiser = new DataInitialiser();
		
		// Get project root
		String initialisationFolderPath = ProjectRootPathFinder.findProjectRootPath() + "/data/initialisation";
		
		// Delete all
		dataInitialiser.resetAllData();
		
		System.out.println("Reset all!");
		
		// Movies
		List<Movie> movieList = dataInitialiser.initialiseMovieData(initialisationFolderPath);

		// Showtimes initialisation, store MOVIE ID (NOT INSTANCE) of movie and a new INSTANCE of cinema
		movieList = dataInitialiser.initialiseShowtimeData(movieList, initialisationFolderPath);
		
//		// Reviews initialisation, store REVIEW ID (NOT INSTANCE) of review in movie
//		movieList = dataInitialiser.initialiseReviewData(movieList, initialisationFolderPath);
		
		// Showtimes initialisation, store MOVIE ID (NOT INSTANCE) of movie and a new INSTANCE of cinema
		movieList = dataInitialiser.initialiseReviewData(movieList, initialisationFolderPath);		
		
		// Finally, serialize the movie files with showtimes included
		for (int i = 0; i < movieList.size(); i++) {
			String storagePath =  ProjectRootPathFinder.findProjectRootPath() + "/data/movies/movie_" + movieList.get(i).getMovieID() + ".dat";
			SerializerHelper.serializeObject(movieList.get(i), storagePath);
		}
		
		System.out.println("Initialized!");

	}
}
