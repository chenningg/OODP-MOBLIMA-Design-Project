import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


class MovieManager {
	// Attributes 
    private ArrayList<Movie> movies;
    Scanner sc = new Scanner(System.in);
    Movie m = new Movie();

    private static MovieManager single_instance = null;

    public static MovieManager getInstance()
    {
        if (single_instance == null)
            single_instance = new MovieManager();
        return single_instance;
    }
    
    
    // Constructor
    private MovieManager() {
        ArrayList<Movie> serializedObject = this.loadObject();
        if (serializedObject != null) {
            this.movies = serializedObject;
        } else {
            this.movies = new ArrayList<>();
            this.saveObject();
        }
    }
    

	// Public exposed methods to app
    public void movieMenuStaff() {
        int choice;
        
        do {
            System.out.println("=================== MOVIE MENU (STAFF) ==================\n" +
                               "| 1. View Movies 										|\n" +
                               "| 2. Add Movies		                                 	|\n" +
                               "| 3. Edit Movies	                                    |\n" +
                               "| 4. Delete Movies	                                    |\n" +
                               "| 0. Back to StaffApp......                             |\n" +
                               "=========================================================");
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    this.viewMovies("Staff");
                    break;
                case 2:
                    this.addMovies();
                    break;
                case 3:
                    this.editMovies();
                    break;
                case 4:
                    this.deleteMovies();
                    break;
                case 0:
                	System.out.println("Back to StaffApp......");
                    break;
                default:
                    System.out.println("Please enter a number between 0-4.");
                    break;
            }
        } while (choice != 0);
    }
    
    
    
    private void viewMovies(String appType) {
    	int choice;
    	
    	if (appType.equals("Staff")) {
            System.out.println(	"=================== MOVIE MENU (STAFF) ==================\n" +
			                    "| 1. View Movies 						       			 |\n" +
			                    "| 2. Add Movies		                                 |\n" +
			                    "| 3. Edit Movies	                                     |\n" +
			                    "| 4. End movie showing	                                 |\n" +
			                    "| 0. Back to StaffApp......                             |\n" +
			                    "=========================================================");
			
            System.out.println("Enter choice: ");
			choice = sc.nextInt();
 
			do {
				
			} while (choice != 0);
    		
    		
    		
    		
    		
    		
    	} else if (appType.equals("Customer")) {
    		
    	} 
    	
        try {
            Movie newMovie = new Movie();
            // Get filepath
            String filePath = ProjectRootPathFinder.findProjectRootPath();
            if (filePath == null) {
                throw new IOException("Cannot find root");
            } else {
                // read active movies
                filePath = filePath + "/data/initialisation/movies/" + movieID + ".txt";
            }

            // Open file and traverse it
            FileReader frStream = new FileReader( filePath );
            BufferedReader brStream = new BufferedReader( frStream );
            String inputLine;
            int i = 0;

            do {
                newMovie.setMovieReviews(new ArrayList<Review>());
                newMovie.setGrossProfit(0);
                newMovie.setTicketsSold(0);
                newMovie.setAverageReviewScore(0);
                newMovie.setTotalReviewNo(0);
                newMovie.setTotalReviewScore(0);

                inputLine = brStream.readLine(); // read in a line
                if (inputLine == null) {break;} // end of file

                switch (i) {
                    case 0:
                        //1st line of file is movieID
                        if(movies.contains(getMovieByID(movieID))){
                            System.out.println("Movie already exists.");
                            inputLine =null;
                            break;
                        }
                        newMovie.setMovieID(movieID);
                        break;
                    case 1:
                        //2st line of file is title
                        newMovie.setTitle(inputLine);
                        break;
                    case 2:
                        //3rd line of file is genres
                        ArrayList<Genre> genreList = new ArrayList<Genre>();
                        String[] genres = inputLine.split(", ?");
                        for(int j=0;j<genres.length;j++){
                            genreList.add(Genre.valueOf(genres[j]));
                        }
                        newMovie.setGenres(genreList);
                        break;
                    case 3:
                        //4th line of file is director
                        newMovie.setDirector(inputLine);
                        break;
                    case 4:
                        //5th line of file is cast
                        ArrayList<String> castList = new ArrayList<String>();
                        for(int j=0;j<inputLine.split(", ?").length;j++){
                            castList.add(inputLine.split(", ?")[j]);
                        }
                        newMovie.setCast(castList);
                        break;
                    case 5:
                        //6th line of file is synopsis
                        newMovie.setSynopsis(inputLine);
                        break;
                    case 6:
                        //7th line of file is movieRating
                        newMovie.setMovieRating(MovieRating.valueOf(inputLine));
                        break;
                    case 7:
                        //8th line of file is movieFormats
                        ArrayList<MovieFormat> movieFormats = new ArrayList<MovieFormat>();
                        String[] mfList = inputLine.split(", ?");
                        for(int j=0;j<mfList.length;j++){
                            movieFormats.add(MovieFormat.valueOf(mfList[j]));
                        }
                        newMovie.setMovieFormats(movieFormats);
                        break;
                    case 8:
                        //9th line of file is movieDuration
                        newMovie.setMovieDuration(Integer.parseInt(inputLine));
                        break;
                    case 9:
                        //10th line of file is showing status
                        newMovie.setShowingStatus(ShowingStatus.valueOf(inputLine));
                        break;
                    case 10:
                        //11th line of file is release date
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String dateInString = inputLine;
                        LocalDate dateTime = LocalDate.parse(dateInString,formatter);
                        newMovie.setReleaseDate(dateTime);
                        System.out.println("Movie release date read and added!");
                        break;
                }
                i++;
            } while (inputLine != null);
            this.movies.add(newMovie);
            this.saveObject();
        }

        catch ( FileNotFoundException e ) {
            System.out.println( "Error opening the input file!" + e.getMessage() );
            System.exit( 0 );
        }
        catch ( IOException e ) {
            System.out.println( "IO Error!" + e.getMessage() );
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /***
     * Displays Top 5 Movies menu for Customers
     */
    public void viewTop5Cust() {
        int choice;
        do{
            System.out.println("==================== View Top 5 Movies =====================\n" +
                    "| 1. By Sales                                              |\n" +
                    "| 2. By Tickets Sold                                       |\n" +
                    "| 3. By Reviews                                            |\n" +
                    "| 0. Back to StaffApp                                      |\n" +
                    "===========================================================");
            System.out.println("Enter choice:");
            choice= sc.nextInt();
            switch (choice){
                case 1:
                    ArrayList<Movie> top5Sales = new ArrayList<Movie>(movies);
                    top5Sales.sort(Comparator.comparingDouble(Movie::getGrossProfit).reversed());
                    if(top5Sales.size()==0){
                        System.out.println("No Available Movies.");
                        break;
                    }
                    for(int i=0;i<5;i++) {
                        System.out.println(i+1 +". "+top5Sales.get(i).getTitle());
                    }
                    System.out.println("Choose a movie:");
                    int input1 = sc.nextInt();
                    displayMovieDetails(top5Sales.get(input1-1));
                    submovieMenu(top5Sales.get(input1-1));
                    break;
                case 2:
                    ArrayList<Movie> top5Tickets = new ArrayList<Movie>(movies);
                    top5Tickets.sort(Comparator.comparingLong(Movie::getTicketsSold).reversed());
                    for(int i=0;i<5;i++) {
                        System.out.println(i+1 +". "+top5Tickets.get(i).getTitle());
                    }
                    System.out.println("Choose a movie:");
                    int input2 = sc.nextInt();
                    displayMovieDetails(top5Tickets.get(input2-1));
                    submovieMenu(top5Tickets.get(input2-1));
                    break;
                case 3:
                    ArrayList<Movie> top5Reviews = new ArrayList<Movie>(movies);
                    for(int i=top5Reviews.size()-1;i>=0;i--){
                        if(top5Reviews.get(i).getMovieReviews().size() <= 1){
                            top5Reviews.remove(i);
                        }
                    }
                    top5Reviews.sort(Comparator.comparingDouble(Movie::getAverageReviewScore).reversed());
                    for(int i=0;i<5;i++) {
                        System.out.println(i+1 +". "+top5Reviews.get(i).getTitle());
                    }
                    System.out.println("Choose a movie:");
                    int input3 = sc.nextInt();
                    displayMovieDetails(top5Reviews.get(input3-1));
                    submovieMenu(top5Reviews.get(input3-1));
                    break;
                case 0:
                	System.out.println("Back to StaffApp......");
                    break;
                default:
                    System.out.println("Please enter a number between 0-4.");
            }
        }while(choice!=4);

    }

    /***
     * Displays Top 5 Movies menu for Staff
     */
    public void viewTop5Staff(){



        int choice;
        do{
            System.out.println("==================== View Top 5 Movies =====================\n" +
                    "| 1. By Sales                                              |\n" +
                    "| 2. By Tickets Sold                                       |\n" +
                    "| 3. By Reviews                                            |\n" +
                    "| 4. Back                                                  |\n" +
                    "===========================================================");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    ArrayList<Movie> top5Sales = new ArrayList<Movie>(movies);
                    top5Sales.sort(Comparator.comparingDouble(Movie::getGrossProfit).reversed());
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i + 1 + ". " + top5Sales.get(i).getTitle());
                    }
                    break;
                case 2:
                    ArrayList<Movie> top5Tickets = new ArrayList<Movie>(movies);
                    top5Tickets.sort(Comparator.comparingLong(Movie::getTicketsSold).reversed());
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i + 1 + ". " + top5Tickets.get(i).getTitle());
                    }
                    break;
                case 3:
                    ArrayList<Movie> top5Reviews = new ArrayList<Movie>(movies);
                    for(int i=top5Reviews.size()-1;i>=0;i--){
                        if(top5Reviews.get(i).getMovieReviews().size() <= 1){
                            top5Reviews.remove(i);
                        }
                    }
                    top5Reviews.sort(Comparator.comparingDouble(Movie::getAverageReviewScore).reversed());
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i + 1 + ". " + top5Reviews.get(i).getTitle());
                    }
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Please enter a number between 0-4.");
            }
        }while(choice!=4);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void displayMovies(){
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.println("========================= Movies ===========================\n" +
                    "| 1. Now Showing                                           |\n" +
                    "| 2. Coming Soon                                           |\n" +
                    "| 3. Cineplexes                                            |\n" +
                    "| 4. Search by Movie Title                                 |\n" +
                    "| 5. Back                                                  |\n" +
                    "===========================================================");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    ArrayList<Movie> nowShowing = new ArrayList<Movie>();
                    for(int i=0;i<movies.size();i++){
                        if(movies.get(i).getShowingStatus().equalsString("NOW_SHOWING") || movies.get(i).getShowingStatus().equalsString("PREVIEW")){
                            nowShowing.add(movies.get(i));
                        }
                    }
                    for(int i=0;i<nowShowing.size();i++){
                        System.out.println(i+1 +". "+nowShowing.get(i).getTitle());
                    }
                    System.out.println("Choose a movie:");
                    int option1 = sc.nextInt();
                    if(option1<1 || option1 >= nowShowing.size()){
                        break;
                    }
                    displayMovieDetails(nowShowing.get(option1-1));
                    submovieMenu(nowShowing.get(option1-1));
                    break;
                case 2:
                    ArrayList<Movie> comingSoon = new ArrayList<Movie>();
                    for(int i=0;i<movies.size();i++){
                        if(movies.get(i).getShowingStatus().equalsString("COMING_SOON")){
                            comingSoon.add(movies.get(i));
                        }
                    }
                    for(int i=0;i<comingSoon.size();i++){
                        System.out.println(i+1 +". "+comingSoon.get(i).getTitle());
                    }
                    System.out.println("Choose a movie:");
                    int option2 = sc.nextInt();
                    if(option2<1 || option2 >= comingSoon.size()){
                        break;
                    }
                    displayMovieDetails(comingSoon.get(option2-1));
                    break;
                case 3:
                    List<Cineplex> cineplexes = new ArrayList<>();
                    cineplexes =  new Company().getCineplexes();
                    for(int i=0;i<cineplexes.size();i++){
                        System.out.println(i+1 +". "+ cineplexes.get(i).getCineplexName());
                    }
                    System.out.println("Choose a cinema:");
                    int option3 = sc.nextInt();
                    if(option3<1 || option3 >= cineplexes.size()){
                        break;
                    }
                    ShowtimeManager.getInstance().displayMoviesfromCineplex(cineplexes.get(option3-1).getCineplexID());
                    break;
                case 4:
                    String title = sc.next();

                    for(int i=0;i<searchMovies(title).size();i++){
                        System.out.println(i+1 +". "+ searchMovies(title).get(i).getTitle());
                    }
                    System.out.println("Choose a movie:");
                    int option4 = sc.nextInt();
                    if(option4<1 || option4 >= searchMovies(title).size()){
                        break;
                    }
                    submovieMenu(searchMovies(title).get(option4-1));
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please enter a number between 1-5.");
            }
        }while(choice != 5);

    }

    public void submovieMenu(Movie movie){
        boolean exit = false;
        System.out.println(" 1. Display Showtimes\n" +
                           " 2. View Reviews\n" +
                           " 3. Back");
        Scanner sc = new Scanner(System.in);
        int choice;
        while(!exit){
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            switch(choice) {
                case 1:
                	ShowtimeManager.getInstance().displayMovieShowtimes(movie, null);
                    exit = true;
                case 2:
                	ReviewManager.getInstance().displayReview(movie);
                    break;
                case 3:
                    exit = true;
                default:
                    System.out.println("Please enter a number between 1-3");
            }
        }

    }


    public List<Movie> searchMovies(String movieName) {
        List<Movie> foundMovies = new ArrayList<>();
        String lowerCaseName = movieName.toLowerCase();

        // for-each loop
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(lowerCaseName)) {
                foundMovies.add(movie);
            }
        }
        return foundMovies;
    }

    public void displayMovieDetails(Movie movie) {
        System.out.print("Movie Title: ");
        System.out.println(movie.getTitle());
        System.out.print("Showing Status: ");
        System.out.println(movie.getShowingStatus());
        System.out.print("Synopsis: ");
        System.out.println(movie.getSynopsis());
        System.out.print("Director: ");
        System.out.println(movie.getDirector());
        System.out.print("Cast: ");
        for(int i=0;i<movie.getCast().size();i++){
            System.out.println(movie.getCast().get(i));
        }
    }

    public Movie getMovieByID(String movieID) {
        for (Movie movie : movies) {
            if (movie.getMovieID().equalsIgnoreCase(movieID)) {
                return movie;
            }
        }
        return null;
    }












//CRUD - CREATE READ UPDATE DELETE MOVIE

    private void addMovies() {
        Movie newMovie = new Movie();
        ArrayList<Genre> genreList = new ArrayList<>();
        ArrayList<String> castList = new ArrayList<>();
        ArrayList<MovieFormat> formatList = new ArrayList<>();

        // System.out.println("Enter movieID: ");
        // newMovie.setMovieID(sc.next());
        System.out.println("Enter movie title: ");
        newMovie.setTitle(sc.next());

        System.out.println("Enter number of genres: ");
        int numGenres = sc.nextInt();
        System.out.println("Enter the genres: ");
        for (int i=0;i<numGenres;i++)
        {
            System.out.println("Enter the genre: ");
            String userGenre = sc.next();
            genreList.add(Genre.valueOf(userGenre));
        }
        newMovie.setGenres(genreList);

        System.out.println("Enter director name: ");
        newMovie.setDirector(sc.next());

        System.out.println("Enter length of cast: ");
        int castLength = sc.nextInt();
        for (int i=0;i<castLength;i++)
        {
            System.out.println("Enter cast member: ");
            String castName = sc.next();
            castList.add(castName);
        }
        newMovie.setCast(castList);

        System.out.println("Enter synopsis: ");
        newMovie.setSynopsis(sc.next());

        System.out.println("Enter movie rating: ");
        String movieRating = sc.next();
        newMovie.setMovieRating(MovieRating.valueOf(movieRating));

        System.out.println("Enter number of movie formats: ");
        int formatLength = sc.nextInt();
        for (int i=0;i<formatLength;i++)
        {
            System.out.println("Enter movie format: ");
            String format = sc.next();
            formatList.add(MovieFormat.valueOf(format));
        }
        newMovie.setMovieFormats(formatList);

        System.out.println("Enter movie duration: ");
        newMovie.setMovieDuration(sc.nextInt());

        System.out.println("Enter showing status: ");
        String showStatus = sc.next();
        newMovie.setShowingStatus(ShowingStatus.valueOf(showStatus));

        System.out.println("Enter release date (format DD/MM/YYYY): ");
        String releaseDate = sc.next();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(releaseDate, dateFormat);
        newMovie.setReleaseDate(date);

        movies.add(newMovie);
    }


    private void editMovies() {
        System.out.println("Enter movieID: ");
        String movieID = sc.next();
//        for (Movie movie : movies) {
        Movie movie = this.findMovie(movieID);
        if (movie != null) {
            int choice;
            do {
                System.out.println("(1) Edit movieID");
                System.out.println("(2) Edit title");
                System.out.println("(3) Edit genres (genres will be overwritten)");
                System.out.println("(4) Edit director");
                System.out.println("(5) Edit cast (cast will be overwritten)");
                System.out.println("(6) Edit synopsis");
                System.out.println("(7) Edit rating");
                System.out.println("(8) Edit formats (formats will be overwritten)");
                System.out.println("(9) Edit duration");
                System.out.println("(10) Edit showing status");
                System.out.println("(11) Edit release date");
                System.out.println("(12) End edits");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter new movieID: ");
                        String newMovieID = sc.next();
                        movie.setMovieID(newMovieID);
                        break;
                    case 2:
                        System.out.println("Enter new title: ");
                        String newTitle = sc.next();
                        movie.setTitle(newTitle);
                        break;
                    case 3:
                        System.out.println("Enter number of genres: ");
                        ArrayList<Genre> Genres = new ArrayList<>();
                        int numGenres = sc.nextInt();
                        for (int i = 0; i < numGenres; i++) {
                            System.out.println("Enter the genre: ");
                            String userGenre = sc.next();
                            Genres.add(Genre.valueOf(userGenre));
                        }
                        movie.setGenres(Genres);
                        break;
                    case 4:
                        System.out.println("Enter director: ");
                        String newDirector = sc.next();
                        movie.setDirector(newDirector);
                        break;
                    case 5:
                        System.out.println("Enter number of cast members: ");
                        int castSize = sc.nextInt();
                        ArrayList<String> newCastList = new ArrayList<>();
                        for (int i = 0; i < castSize; i++) {
                            System.out.println("Enter cast member: ");
                            String newCast = sc.next();
                            newCastList.add(newCast);
                        }
                        movie.setCast(newCastList);
                        break;
                    case 6:
                        System.out.println("Enter new synopsis: ");
                        String newSynopsis = sc.next();
                        movie.setSynopsis(newSynopsis);
                        break;
                    case 7:
                        System.out.println("Enter new rating: ");
                        String newRating = sc.next();
                        movie.setMovieRating(MovieRating.valueOf(newRating));
                        break;
                    case 8:
                        System.out.println("Enter new formats: ");
                        ArrayList<MovieFormat> newFormats = new ArrayList<>();
                        int newFormatLength = sc.nextInt();
                        for (int i = 0; i < newFormatLength; i++) {
                            System.out.println("Enter movie format: ");
                            String newFormat = sc.next();
                            newFormats.add(MovieFormat.valueOf(newFormat));
                        }
                        movie.setMovieFormats(newFormats);
                        break;
                    case 9:
                        System.out.println("Enter new duration: ");
                        int newDuration = sc.nextInt();
                        movie.setMovieDuration(newDuration);
                        break;
                    case 10:
                        System.out.println("Enter new showing status: ");
                        String newShowStatus = sc.next();
                        movie.setShowingStatus(ShowingStatus.valueOf(newShowStatus));
                        break;
                    case 11:
                        System.out.println("Enter new release date: ");
                        String newReleaseDate = sc.next();
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/mm/yyyy");
                        LocalDate date = LocalDate.parse(newReleaseDate, dateFormat);
                        movie.setReleaseDate(date);
                        break;
                }
            } while (choice != 12);
//                break;
            }
        else{
            System.out.println("Movie not found.");
        }
        this.saveObject();
    }

    private void deleteMovies() {
        System.out.println("Enter movieID to be deleted: ");
        String delMovieID = sc.next();
        Movie foundMovie = this.findMovie(delMovieID);
        if(foundMovie!=null){
            movies.remove(foundMovie);
            System.out.println("Movie deleted");
        }
        else{
            System.out.println("Movie does not exist.");
        }
        this.saveObject();
    }

    private Movie findMovie(String movieID) {
        ArrayList<Movie> moviesInMovieManager = MovieManager.getInstance().getMovies();
        for (Movie movie : moviesInMovieManager)
        {
            String movieTitle = movie.getMovieID();
            if(movieTitle==null){
                return null;
            }
            if (movieTitle.equalsIgnoreCase(movieID))
            {
                return movie;
            }
        }
        return null; // if movie not found
    }

    public void peekMovie() {
        System.out.println("Last movie added was: " + movies.get(movies.size()-1).getTitle());
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Movie> loadObject() {
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/movies/movies.dat";
        System.out.println("Movies loaded!");
        return (ArrayList<Movie>) SerializerHelper.deSerializeObject(filepath);
    }

    public void saveObject() {
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/movies/movies.dat";
        SerializerHelper.serializeObject(this.movies, filepath);
        System.out.println("Movies Saved!");
    }

    private Date dateParser(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(dateString);
        return date;
    }
    
    private LocalDateTime dateTimeParser(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return dateTime;
    }

}
