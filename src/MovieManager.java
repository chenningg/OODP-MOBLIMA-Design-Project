import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


class MovieManager {
	// Attributes 

    private Map<String,Movie> movies = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    private static MovieManager single_instance = null;

    public static MovieManager getInstance()
    {
        if (single_instance == null)
            single_instance = new MovieManager();
        return single_instance;
    }
    
    
    // Constructor
    private MovieManager() {
        HashMap<String,Movie> serializedObject = this.loadObject();
        if (serializedObject != null) {
            this.movies = serializedObject;
        } else {
            this.movies = new HashMap<String,Movie>();
        }
    }
    

	// Public exposed methods to app
    public void movieMenuStaff() {
        int choice;
        
        do {
            System.out.println("=================== MOVIE MENU (STAFF) ==================\n" +
                               "| 1. View/ Edit Movies 						    		|\n" +
                               "| 2. Add Movies		                                 	|\n" +
                               "| 3. Search Movies (By Title)	                        |\n" +
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
                    this.searchMovies("Staff");
                    break;
                case 0:
                	System.out.println("Back to StaffApp......");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1-3.");
                    break;
            }
        } while (choice != 0);
    }

    public void viewMovies(String appType) {
        int choice;

        if (appType.equals("Staff")) {
            System.out.println("=================== MOVIE MENU (STAFF) ==================\n" +
                    "| 1. List all movies	                                 |\n" +
                    "| 2. Coming Soon 						       			 |\n" +
                    "| 3. Preview		                                     |\n" +
                    "| 4. Now Showing	                                     |\n" +
                    "| 5. End of Showing                                     |\n" +
                    "| 0. Back to Staff Movie Menu......                     |\n" +
                    "=========================================================");

            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            do {
                switch (choice) {
                    case 1:
                        List<Movie> movieList = new ArrayList<>(movies.values());
                        this.selectMovie(movieList,appType);
                        break;
                    case 2:
                        List<Movie> comingSoon = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("COMING_SOON")){
                                comingSoon.add(entry.getValue());
                            }
                        }
                        this.selectMovie(comingSoon,appType);
                        break;
                    case 3:
                        List<Movie> preview = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("PREVIEW")){
                                preview.add(entry.getValue());
                            }
                        }
                        this.selectMovie(preview,appType);
                        break;
                    case 4:
                        List<Movie> nowShowing = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                                nowShowing.add(entry.getValue());
                            }
                        }
                        this.selectMovie(nowShowing,appType);
                        break;
                    case 5:
                        List<Movie> endShowing = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("END_OF_SHOWING")){
                                endShowing.add(entry.getValue());
                            }
                        }
                        this.selectMovie(endShowing,appType);
                        break;
                    case 0:
                        System.out.println("Back to StaffApp...");
                        break;
                }
            } while (choice != 0);

        } else if (appType.equals("Customer")) {
            System.out.println("=================== MOVIE MENU (CUSTOMER) ================\n" +
                               "| 1. List all movies	                                 |\n" +
                               "| 2. Coming Soon 						       			 |\n" +
                               "| 3. Preview		                                     |\n" +
                               "| 4. Now Showing	                                     |\n" +
                               "| 5. Search Movies (By Title)                            |\n" +
                               "| 0. Back to Customer Movie Menu......                   |\n" +
                               "=========================================================");
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            do {
                switch (choice) {
                    case 1:
                        List<Movie> allMovies = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("COMING_SOON")||
                                entry.getValue().getShowingStatus().equalsString("PREVIEW")||
                                entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                                allMovies.add(entry.getValue());
                            }
                        }
                        this.selectMovie(allMovies,appType);
                        break;
                    case 2:
                        List<Movie> comingSoon = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("COMING_SOON")){
                                comingSoon.add(entry.getValue());
                            }
                        }
                        this.selectMovie(comingSoon,appType);
                        break;
                    case 3:
                        List<Movie> preview = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("PREVIEW")){
                                preview.add(entry.getValue());
                            }
                        }
                        this.selectMovie(preview,appType);
                        break;
                    case 4:
                        List<Movie> nowShowing = new ArrayList<Movie>();
                        for(Map.Entry<String,Movie> entry : movies.entrySet()){
                            if(entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                                nowShowing.add(entry.getValue());
                            }
                        }
                        this.selectMovie(nowShowing,appType);
                        break;
                    case 5:
                        this.searchMovies(appType);
                        break;
                    case 0:
                        System.out.println("Back to CustomerApp...");
                        break;
                }
            } while (choice != 0);
        }
    }

    public void subMovieMenu(Movie movie,String appType){
        boolean exit = false;
        if(appType == "Staff") {
            System.out.println(" 1. Display/Edit Showtimes\n" +
                    " 2. Edit Movie\n" +
                    " 3. Remove Movie\n" +
                    " 4. View Reviews\n" +
                    " 0. Back");
            int choice;
            while (!exit) {
                System.out.println("Enter your choice: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        ShowtimeManager.getInstance().getMovieShowtimes(movie.getMovieID(), appType);
                        exit = true;
                    case 2:
                        this.editMovies(movie);
                        break;
                    case 3:
                        this.removeMovie(movie);
                    case 4:
                        ReviewManager.getInstance().printReviews(movie.getReviews());
                        break;
                    case 0:
                        exit = true;
                    default:
                        System.out.println("Please enter a number between 1-4");
                }
            }
        }
        else if(appType.equals("Customer")){
            System.out.println(" 1. Display Showtimes\n" +
                    " 2. View Reviews\n" +
                    " 0. Back");
            int choice;
            while (!exit) {
                System.out.println("Enter your choice: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        ShowtimeManager.getInstance().getMovieShowtimes(movie.getMovieID(),appType);
                        exit = true;
                    case 2:
                        ReviewManager.getInstance().printReviews(movie.getReviews());
                        break;
                    case 0:
                        exit = true;
                    default:
                        System.out.println("Please enter a number between 1-2");
                }
            }

        }
    }


    public List<Movie> searchMovies(String appType){
        System.out.println("Please enter a search term: ");
        String movieTitle = sc.next();
        List<Movie> foundMovies = new ArrayList<>();
        String lowerCaseName = movieTitle.toLowerCase();

        // for-each loop
        for (Movie movie : movies.values()) {
            if (movie.getTitle().toLowerCase().contains(lowerCaseName)) {
                foundMovies.add(movie);
            }
        }
        if(foundMovies.size() == 0){
            System.out.println("No such movie found!");
            return null;
        }
        selectMovie(foundMovies,appType);
        return foundMovies;
    }

    public Movie selectMovie(List<Movie> movieSelect,String appType) {
        for (int i = 0; i < movieSelect.size(); i++) {
            System.out.println(i + 1 + ". " + movieSelect.get(i).getTitle());
        }
        int choice;
        do {
            System.out.println("Choose a movie (Enter 0 to exit): ");
            choice = sc.nextInt()+1;
            if(choice==0)
                return null;
        }while(choice<1 || choice >= movieSelect.size());
        displayMovieDetails(movieSelect.get(choice));
        subMovieMenu(movieSelect.get(choice),appType);
        return movieSelect.get(choice);
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




//CRUD - CREATE READ UPDATE DELETE MOVIE

    private void addMovies() {
        Movie newMovie = new Movie();
        ArrayList<Genre> genreList = new ArrayList<>();
        ArrayList<String> castList = new ArrayList<>();
        ArrayList<MovieFormat> formatList = new ArrayList<>();

        newMovie.setMovieID(IDHelper.getLatestID("movie"));
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

        movies.put(newMovie.getMovieID(),newMovie);
    }


    private void editMovies(Movie movie) {
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
                case 12:
                    System.out.println("End of edit\n" +
                            "Back to Staff Sub Menu");
                    break;
                default:
                    System.out.println("Please enter a number from 1-11: ");
            }
        } while (choice != 0);
//
        this.saveObject(movie.getMovieID());
    }

    private void removeMovie(Movie movie) {
        movie.setShowingStatus(ShowingStatus.END_OF_SHOWING);
        this.saveObject(movie.getMovieID());
    }

    /***
     * Displays Top 5 Movies menu for Customers
     */
    public void viewTop5Cust() {
        int choice;
        String apptype = "Customer";
        do{
            System.out.println("==================== View Top 5 Movies =====================\n" +
                    "| 1. By Sales                                              |\n" +
                    "| 2. By Tickets Sold                                       |\n" +
                    "| 3. By Reviews                                            |\n" +
                    "| 0. Back to CustomerApp                                   |\n" +
                    "===========================================================");
            System.out.println("Enter choice:");
            choice= sc.nextInt();
            switch (choice){
                case 1:
                    ArrayList<Movie> top5Sales = new ArrayList<Movie>(movies.values());
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
                    subMovieMenu(top5Sales.get(input1-1),apptype);
                    break;
                case 2:
                    ArrayList<Movie> top5Tickets = new ArrayList<Movie>(movies.values());
                    top5Tickets.sort(Comparator.comparingLong(Movie::getTicketsSold).reversed());
                    for(int i=0;i<5;i++) {
                        System.out.println(i+1 +". "+top5Tickets.get(i).getTitle());
                    }
                    System.out.println("Choose a movie:");
                    int input2 = sc.nextInt();
                    displayMovieDetails(top5Tickets.get(input2-1));
                    subMovieMenu(top5Tickets.get(input2-1),apptype);
                    break;
                case 3:
                    ArrayList<Movie> top5Reviews = new ArrayList<Movie>(movies.values());
                    for(int i=top5Reviews.size()-1;i>=0;i--){
                        if(top5Reviews.get(i).getReviews().size() <= 1){
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
                    subMovieMenu(top5Reviews.get(input3-1),apptype);
                    break;
                case 0:
                    System.out.println("Back to StaffApp...");
                    break;
                default:
                    System.out.println("Please enter a number between 1-3.");
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
                    "| 0. Back to StaffApp                                      |\n" +
                    "===========================================================");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    ArrayList<Movie> top5Sales = new ArrayList<Movie>(movies.values());
                    top5Sales.sort(Comparator.comparingDouble(Movie::getGrossProfit).reversed());
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i + 1 + ". " + top5Sales.get(i).getTitle());
                    }
                    break;
                case 2:
                    ArrayList<Movie> top5Tickets = new ArrayList<Movie>(movies.values());
                    top5Tickets.sort(Comparator.comparingLong(Movie::getTicketsSold).reversed());
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i + 1 + ". " + top5Tickets.get(i).getTitle());
                    }
                    break;
                case 3:
                    ArrayList<Movie> top5Reviews = new ArrayList<Movie>(movies.values());
                    for(int i=top5Reviews.size()-1;i>=0;i--){
                        if(top5Reviews.get(i).getReviews().size() <= 1){
                            top5Reviews.remove(i);
                        }
                    }
                    top5Reviews.sort(Comparator.comparingDouble(Movie::getAverageReviewScore).reversed());
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i + 1 + ". " + top5Reviews.get(i).getTitle());
                    }
                    break;
                case 0:
                    System.out.println("Back to StaffApp...");
                    break;
                default:
                    System.out.println("Please enter a number between 1-3.");
            }
        }while(choice!=4);
    }

    public List<Movie> getMovies() {
        List<Movie> movieList = new ArrayList<>(movies.values());
        return movieList;
    }

    public HashMap<String,Movie> loadObject() {
        HashMap<String, Movie> loadedMovies = new HashMap<String, Movie>();
        File folder = new File(ProjectRootPathFinder.findProjectRootPath() + "/data/movies");

        File[] listOfFiles = folder.listFiles();
        
        if(listOfFiles != null){
        	for(int i=0;i<listOfFiles.length;i++){
        		String filepath = listOfFiles[i].getPath(); // Returns full path incl file name and type
        		Movie newMovie = (Movie)SerializerHelper.deSerializeObject(filepath);
        		String fileID = listOfFiles[i].getName().split("\\.(?=[^\\.]+$)")[0].split("_")[1];
                loadedMovies.put(fileID, newMovie);
            }
        }
        
        System.out.println("Movies loaded!");
        return loadedMovies;
    }

    public void saveObject(String movieID) {
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/movies/movie_"+movieID+".dat";
        SerializerHelper.serializeObject(this.movies, filepath);
        System.out.println("Movies Saved!");
    }

    public Movie getMoviebyID(String movieID){
        return movies.get(movieID);
    }

    public void updateGrossProfit(String movieID,double grossProfit){
        movies.get(movieID).setGrossProfit(movies.get(movieID).getGrossProfit() + grossProfit);
    }

    public void updateTicketsSold(String movieID,long ticketsSold){
        movies.get(movieID).setTicketsSold(movies.get(movieID).getTicketsSold() + ticketsSold);
    }

    public void updateReview(String movieID, String reviewID, double reviewScore){
        Movie movie = movies.get(movieID);
        movie.setTotalReviewNo(movie.getTotalReviewNo()+1);
        movie.setTotalReviewScore(movie.getTotalReviewScore()+reviewScore);
        movie.addMovieReview(reviewID);
        movie.setAverageReviewScore(movie.getTotalReviewScore()/movie.getTotalReviewNo());
    }

    public void updateShowtimes(String movieID, String showtimeID) {
        Movie movie = movies.get(movieID);
        List<String> showtimeIDs = movie.getShowtimeIDs();
        showtimeIDs.add(showtimeID);
        movie.setShowtimeIDs(showtimeIDs);
    }
}
