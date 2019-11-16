import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


class MovieManager {
	// Attributes 

    private Map<String,Movie> movies;
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
    	this.movies= new HashMap<String,Movie>();
    	this.loadObject();
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
            do {
                System.out.println("=================== MOVIE MENU (STAFF) ==================\n" +
                                   "| 1. List all movies	                                |\n" +
                                   "| 2. Coming Soon 						       			|\n" +
                                   "| 3. Preview		                                    |\n" +
                                   "| 4. Now Showing	                                    |\n" +
                                   "| 5. End of Showing                                     |\n" +
                                   "| 0. Back to Staff Movie Menu......                     |\n" +
                                   "=========================================================");

                System.out.println("Enter choice: ");
                
                if (!sc.hasNextInt()) {
            		System.out.println("Invalid input type. Please choose a choice from 0-5.");
            		sc.next(); // Remove newline character
            		choice = -1;
            		continue;
            	}
                
                choice = sc.nextInt();
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
            do {
                System.out.println("=================== MOVIE MENU (CUSTOMER) ================\n" +
                                   "| 1. List all movies	                                 |\n" +
                                   "| 2. Coming Soon 						       			 |\n" +
                                   "| 3. Preview		                                     |\n" +
                                   "| 4. Now Showing	                                     |\n" +
                                   "| 5. Search Movies (By Title)                            |\n" +
                                   "| 0. Back to Customer Movie Menu......                   |\n" +
                                   "==========================================================");
                System.out.println("Enter choice: ");
                
                if (!sc.hasNextInt()) {
            		System.out.println("Invalid input type. Please choose a choice from 0-5.");
            		sc.next(); // Remove newline character
            		choice = -1;
            		continue;
            	}
                
                choice = sc.nextInt();
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
        if(appType.equals("Staff")) {
            int choice;
            do{
                System.out.println(	"====================== MOVIE CHOICES =====================\n" +
			                        "| 1. Display/Edit Showtimes                              |\n" +
			                        "| 2. Edit Movie 						       		      |\n" +
			                        "| 3. Remove Movie		                                  |\n" +
			                        "| 4. View Reviews	                                      |\n" +
			                        "| 0. Back to Movie Listings			                  |\n" +
			                        "==========================================================");            	

                System.out.println("Enter choice: ");
                
                if (!sc.hasNextInt()) {
            		System.out.println("Invalid input type. Please choose a choice from 0-4.");
            		sc.next(); // Remove newline character
            		choice = -1;
            		continue;
            	}
                
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        ShowtimeManager.getInstance().getMovieShowtimes(movie.getMovieID(), appType);
                        break;
                    case 2:
                        this.editMovies(movie);
                        break;
                    case 3:
                        this.removeMovie(movie);
                        break;
                    case 4:
                        ReviewManager.getInstance().printReviews(movie.getReviews());
                        break;
                    case 0:
                    	System.out.println("Back to Movie Listings......");
                        break;
                    default:
                        System.out.println("Please enter a number between 0-4");
                }
            }while(choice != 0);
        }
        else if(appType.equals("Customer") && !movie.getShowingStatus().equals(ShowingStatus.COMING_SOON)){
            int choice;
            do{
                System.out.println(	"====================== MOVIE CHOICES =====================\n" +
			                        "| 1. Display Showtimes                                   |\n" +
			                        "| 2. View Reviews                                        |\n" +
			                        "| 3. Leave Review                                        |\n" +
			                        "| 0. Back to Movie Listings                              |\n" +
			                        "==========================================================");       

                System.out.println("Enter your choice: ");
                
                if (!sc.hasNextInt()) {
            		System.out.println("Invalid input type. Please choose a choice from 0-2.");
            		sc.next(); // Remove newline character
            		choice = -1;
            		continue;
            	}
                
                choice = sc.nextInt();
                
                switch (choice) {
                    case 1:
                        ShowtimeManager.getInstance().getMovieShowtimes(movie.getMovieID(),appType);
                        break;
                    case 2:
                        ReviewManager.getInstance().printReviews(movie.getReviews());
                        break;
                    case 3:
                    	ReviewManager.getInstance().addReview(movie.getMovieID());
                    case 0:
                    	System.out.println("Back to Movie Listings......");
                        break;
                    default:
                        System.out.println("Please enter a number between 0-2");
                }
            } while(choice != 0);

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
        int choice,subChoice;
        do{
            for (int i = 0; i < movieSelect.size(); i++) {
                System.out.println(i + 1 + ". " + movieSelect.get(i).getTitle() + " (" + movieSelect.get(i).getShowingStatus().toString() + ")");
            }
            
            do {
                System.out.println("Choose a movie (Enter 0 to exit): ");
                
                if (!sc.hasNextInt()) {
            		System.out.printf("Invalid input type. Please choose a choice from 0-%d.", (movieSelect.size()));
            		sc.next(); // Remove newline character
            		choice = -1;
            		continue;
            	}
                
                choice = sc.nextInt()-1;
                             
                
                if(choice==-1) {
                    return null;
                } else if (choice < 0 || choice >= movieSelect.size()) {
                	System.out.println("Invalid choice. Please enter a number between 0 and " + movieSelect.size());
                } 
                
            }while(choice  < 0 || choice >= movieSelect.size());
            
            movieSelect.get(choice).displayMovieDetails();
            subMovieMenu(movieSelect.get(choice),appType);
            System.out.println("Enter 0 to return to Movie Menu \t\n" +
                    "Enter 1-9 to return to list of movies:");
            subChoice = sc.nextInt();
            
            if(subChoice == 0){
                break;
            }
            
        }while(subChoice != 0);
        
        return movieSelect.get(choice);
    }


//CRUD - CREATE READ UPDATE DELETE MOVIE

    private void addMovies() {
        Movie newMovie = new Movie();
        ArrayList<Genre> genreList = new ArrayList<>();
        ArrayList<String> castList = new ArrayList<>();
        ArrayList<MovieFormat> formatList = new ArrayList<>();

        System.out.println("Enter movie title: ");
        sc.nextLine();
        String title = sc.nextLine();
        newMovie.setTitle(title);
        System.out.println("Enter number of genres: ");
        int numGenres = sc.nextInt();

        for(int i=0;i<Genre.values().length;i++){
            System.out.println(i+1 +". " +Genre.values()[i].toString());
        }
        for (int i=0;i<numGenres;i++)
        {
            System.out.println("Pick genre: ");
            int choice = sc.nextInt()-1;
            genreList.add(Genre.values()[choice]);
        }
        newMovie.setGenres(genreList);

        System.out.println("Enter director name: ");
        sc.nextLine();
        newMovie.setDirector(sc.nextLine());

        System.out.println("Enter number of cast members: ");
        int castLength = sc.nextInt();
        sc.nextLine();
        for (int i=0;i<castLength;i++)
        {
            System.out.println("Enter cast member: ");
            String castName = sc.nextLine();
            castList.add(castName);
        }
        newMovie.setCast(castList);

        System.out.println("Enter synopsis: ");
        newMovie.setSynopsis(sc.nextLine());

        System.out.println("Pick movie rating: ");
        for(int i=0;i<MovieRating.values().length;i++){
            System.out.println(i+1 + ". " +MovieRating.values()[i].toString());
        }
        int movieRating = sc.nextInt()-1;
        newMovie.setMovieRating(MovieRating.values()[movieRating]);

        System.out.println("Enter number of movie formats: ");
        int formatLength = sc.nextInt();
        for(int i=0;i<MovieFormat.values().length;i++){
            System.out.println(i+1 +". " +MovieFormat.values()[i].toString());
        }
        for (int i=0;i<formatLength;i++)
        {
            System.out.println("Pick movie format: ");
            int choice = sc.nextInt()-1;
            formatList.add(MovieFormat.values()[choice]);
        }
        newMovie.setMovieFormats(formatList);

        System.out.println("Enter movie duration: ");
        newMovie.setMovieDuration(sc.nextInt());

        System.out.println("Pick showing status: ");
        for(int i=0;i<ShowingStatus.values().length;i++){
            System.out.println(i+1 + ". " +ShowingStatus.values()[i].toString());
        }
        int showStatus = sc.nextInt()-1;
        newMovie.setShowingStatus(ShowingStatus.values()[showStatus]);

        System.out.println("Enter release date (format DD/MM/YYYY): ");
        String releaseDate = sc.next();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(releaseDate, dateFormat);
        newMovie.setReleaseDate(date);

        int choice1;

        do {
            System.out.println("Your current movie: ");
            System.out.println("Movie Title: " + newMovie.getTitle());
            System.out.print("Genres: ");
            for (int i = 0; i < newMovie.getGenres().size(); i++) {
                System.out.print(newMovie.getGenres().get(i).toString());
                if (i+1 < newMovie.getGenres().size()) {
                    System.out.print(", ");
                }
            }
            System.out.println("Rating: " + newMovie.getMovieRating());
            System.out.println("Duration: " + newMovie.getMovieDuration());
            System.out.print("Movie Formats: ");
            for (int j = 0; j < newMovie.getMovieFormats().size(); j++) {
                System.out.print(newMovie.getMovieFormats().get(j).toString());
                if (j+1 < newMovie.getMovieFormats().size()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
            System.out.println("Showing Status: "+newMovie.getShowingStatus());
            System.out.println("Synopsis: "+newMovie.getSynopsis());
            System.out.println("Director: "+newMovie.getDirector());
            System.out.print("Cast: ");
            for (int k = 0; k < newMovie.getCast().size(); k++) {
                System.out.print(newMovie.getCast().get(k));
                if (k+1 < newMovie.getCast().size()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
            System.out.println(	"========================= ADD MOVIE ====================\n" +
                    "| 1. Submit movie                                      |\n" +
                    "| 2. Edit movie                                        |\n" +
                    "| 0. Discard movie, back to Movie Menu                 |\n" +
                    "========================================================");
            System.out.println("Enter choice: ");
            choice1 = sc.nextInt();

            switch (choice1) {
                case 1:
                    String movieID = IDHelper.getLatestID("movie");
                    newMovie.setMovieID(movieID);
                    this.saveObject(newMovie);
                    this.movies.put(newMovie.getMovieID(), newMovie);

                    System.out.println("Movie created! Back to Movie Menu......");
                    choice1 = 0;
                    break;
                case 2:
                    this.editMovies(newMovie);
                    break;
                case 0:
                    System.out.println("Movie discarded. Back to Movie Menu......");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 0-2");
                    break;
            }

        } while (choice1 != 0);
    }


    private void editMovies(Movie movie) {
        int choice;
        do {
        	System.out.println("Currently selected movie details:");
        	movie.displayMovieDetails();
            System.out.println("=================== EDIT MOVIES (STAFF) ==================\n" +
                                "| 1. Edit Title      	                                  |\n" +
                                "| 2. Edit Genres (Overwritten)       	        		  |\n" +
                                "| 3. Edit Director	                                      |\n" +
                                "| 4. Edit Cast (Overwritten)                             |\n" +
                                "| 5. Edit Synopsis                                       |\n" +
                                "| 6. Edit Rating                                         |\n" +
                                "| 7. Edit Formats (Overwritten)                          |\n" +
                                "| 8. Edit Duration                                       |\n" +
                                "| 9. Edit Showing Status                                 |\n" +
                                "| 10. Edit Release Date                                  |\n" +
                                "| 0. Finish Editing Movie                                |\n" +
                                "==========================================================");
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                	System.out.println("Enter new title");
                    String title = sc.nextLine();
                    movie.setTitle(title);
                    break;
                case 2:
                    ArrayList<Genre> Genres = new ArrayList<>();
                    System.out.println("Enter number of genres: ");
                    int numGenres = sc.nextInt();

                    for(int i=0;i<Genre.values().length;i++){
                        System.out.println(i+1 +". " +Genre.values()[i].toString());
                    }
                    for (int i=0;i<numGenres;i++)
                    {
                        System.out.println("Pick genre: ");
                        int genre = sc.nextInt()-1;
                        Genres.add(Genre.values()[genre]);
                    }
                    movie.setGenres(Genres);
                    break;
                case 3:
                	System.out.println("Enter revised director name");
                    movie.setDirector(sc.nextLine());
                    break;
                case 4:
                    System.out.println("Enter number of cast members: ");
                    int castSize = sc.nextInt();
                    sc.nextLine();
                    ArrayList<String> newCastList = new ArrayList<>();
                    for (int i = 0; i < castSize; i++) {
                        System.out.println("Enter cast member: ");
                        String newCast = sc.nextLine();
                        newCastList.add(newCast);
                    }
                    movie.setCast(newCastList);
                    break;
                case 5:
                    System.out.println("Enter new synopsis: ");
                    String newSynopsis = sc.nextLine();
                    movie.setSynopsis(newSynopsis);
                    break;
                case 6:
                    System.out.println("Pick new movie rating: ");
                    for(int i=0;i<MovieRating.values().length;i++){
                        System.out.println(i+1 + ". " +MovieRating.values()[i].toString());
                    }
                    int movieRating = sc.nextInt()-1;
                    movie.setMovieRating(MovieRating.values()[movieRating]);
                    break;
                case 7:

                    ArrayList<MovieFormat> newFormats = new ArrayList<>();
                    System.out.println("Enter number of movie formats: ");
                    int formatLength = sc.nextInt();
                    for(int i=0;i<MovieFormat.values().length;i++){
                        System.out.println(i+1 +". " +MovieFormat.values()[i].toString());
                    }
                    for (int i=0;i<formatLength;i++)
                    {
                        System.out.println("Pick new movie format: ");
                        int format = sc.nextInt()-1;
                        newFormats.add(MovieFormat.values()[format]);
                    }
                    movie.setMovieFormats(newFormats);
                    break;
                case 8:
                    System.out.println("Enter new duration: ");
                    int newDuration = sc.nextInt();
                    movie.setMovieDuration(newDuration);
                    break;
                case 9:
                    System.out.println("Pick showing status: ");
                    for(int i=0;i<ShowingStatus.values().length;i++){
                        System.out.println(i+1 + ". " +ShowingStatus.values()[i].toString());
                    }
                    int showStatus = sc.nextInt()-1;
                    movie.setShowingStatus(ShowingStatus.values()[showStatus]);
                    break;
                case 10:
                    System.out.println("Enter new release date: ");
                    String newReleaseDate = sc.next();
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(newReleaseDate, dateFormat);
                    movie.setReleaseDate(date);
                    break;
                case 0:
                    System.out.println("End of edit");
                    break;
                default:
                    System.out.println("Please enter a number from 1-11: ");
            }
        } while (choice != 0);

        this.saveObject(movie);
    }

    private void removeMovie(Movie movie) {
        movie.setShowingStatus(ShowingStatus.END_OF_SHOWING);
        this.saveObject(movie);
    }

    
    
    
    public void viewTop5(String apptype) {
    	int choice;
    	int subchoice;
    	
    	do {
    		if (apptype.equals("Customer")) {
                System.out.println(	"==================== View Top 5 Movies =====================\n" +
	                    "| 1. By Sales                                              |\n" +
	                    "| 2. By Tickets Sold                                       |\n" +
	                    "| 3. By Reviews                                            |\n" +
	                    "| 0. Back to CustomerApp                                   |\n" +
	                    "===========================================================");
    		} else if (apptype.equals("Staff")) {
                System.out.println(	"==================== View Top 5 Movies =====================\n" +
	                    "| 1. By Sales                                              |\n" +
	                    "| 2. By Tickets Sold                                       |\n" +
	                    "| 3. By Reviews                                            |\n" +
	                    "| 0. Back to StaffApp                                      |\n" +
	                    "===========================================================");    			
    		}
    		
			System.out.println("Enter choice:");
			choice= sc.nextInt();
    		
			ArrayList<Movie> top5 = new ArrayList<Movie>();
    		
			switch (choice) {
				case 1:
	                for(Map.Entry<String,Movie> entry : movies.entrySet()){
	                    if(entry.getValue().getShowingStatus().equalsString("PREVIEW")||
	                            entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
	                        top5.add(entry.getValue());
	                    }
	                }	
	                top5.sort(Comparator.comparingDouble(Movie::getGrossProfit).reversed());
			
                    if(top5.size()==0){
                        System.out.println("No Available Movies.");
                        break;
                    } else {
                       for (int i=0;i<Math.min(5, top5.size());i++) {
                           System.out.println(i+1 +". "+top5.get(i).getTitle() +" \t\t\t(Sales:  "+ top5.get(i).getGrossProfit()+")");
                       }
                    }
                    
                    break;
				case 2:
                    for(Map.Entry<String,Movie> entry : movies.entrySet()){
                        if(entry.getValue().getShowingStatus().equalsString("PREVIEW")||
                                entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                            top5.add(entry.getValue());
                        }
                    }
                    top5.sort(Comparator.comparingLong(Movie::getTicketsSold).reversed());
					
                    if(top5.size()==0){
                        System.out.println("No Available Movies.");
                        break;
                    } else {
                       for (int i=0;i<Math.min(5, top5.size());i++) {
                           System.out.println(i+1 +". "+top5.get(i).getTitle() +" \t\t\t(Tickets Sold:  "+ top5.get(i).getTicketsSold()+")");
                       }
                    }					
                    
					break;
				case 3:
                    for(Map.Entry<String,Movie> entry : movies.entrySet()){
                        if(entry.getValue().getShowingStatus().equalsString("PREVIEW")||
                                entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                            top5.add(entry.getValue());
                        }
                    }
                    for(int i=top5.size()-1;i>=0;i--){
                        if(top5.get(i).getReviews().size() <= 1){
                        	top5.remove(i);
                        }
                    }
                    top5.sort(Comparator.comparingDouble(Movie::getAverageReviewScore).reversed());
                    
                    if(top5.size()==0){
                        System.out.println("No Available Movies.");
                        break;
                    } else {
                       for (int i=0;i<Math.min(5, top5.size());i++) {
                           System.out.println(i+1 +". "+top5.get(i).getTitle() +" \t\t\t(Review Score:  "+ top5.get(i).getAverageReviewScore()+")");
                       }
                    }
					
					break;
				case 0:
					if (apptype.equals("Customer")) {
						System.out.println("Back to CustomerApp......");
					} else if (apptype.equals("Staff")) {
						System.out.println("Back to StaffApp......");
					}

					break;
				default:
					System.out.println("Invalid input. Please enter a number between 0-3");
					break;
			}
			
			if ( (choice >=1 || choice <= 3) && apptype.equals("Customer") ) {
	            do {
	                System.out.println("Choose a movie (Press 0 to exit): ");
	                subchoice = sc.nextInt()-1;        
	                
	                if (subchoice == -1) {
	             	   System.out.println("Back to CustomerApp......");
	                    break;
	                } else if (subchoice >= top5.size()) {
	             	   System.out.println("Invalid input. Please enter a number between 0 and " + top5.size());
	                } else {
	                    displayMovieDetails(top5.get(subchoice));
	                    subMovieMenu(top5.get(subchoice),apptype);
	                }
	            } while (subchoice != -1);
			}
			
			
			
    	} while (choice != 0);
    }
    
    
    
/*
    public void viewTop5Cust() {
        int choice;
        int numberToList;
        
        String apptype = "Customer";
        do{
            System.out.println(	"==================== View Top 5 Movies =====================\n" +
			                    "| 1. By Sales                                              |\n" +
			                    "| 2. By Tickets Sold                                       |\n" +
			                    "| 3. By Reviews                                            |\n" +
			                    "| 0. Back to CustomerApp                                   |\n" +
			                    "===========================================================");
            System.out.println("Enter choice:");
            choice= sc.nextInt();
            
            switch (choice){
                case 1:
                    ArrayList<Movie> top5Sales = new ArrayList<Movie>();
                    for(Map.Entry<String,Movie> entry : movies.entrySet()){
                        if(entry.getValue().getShowingStatus().equalsString("PREVIEW")||
                                entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                            top5Sales.add(entry.getValue());
                        }
                    }
                    top5Sales.sort(Comparator.comparingDouble(Movie::getGrossProfit).reversed());
                    
                    if(top5Sales.size()==0){
                        System.out.println("No Available Movies.");
                        break;
                    } else if (top5Sales.size() > 5) {
                    	numberToList = 5;
                    } else {
                    	numberToList = top5Sales.size();
                    }
                    int input1;
                   do{
                       for(int i=0;i<numberToList;i++) {
                           System.out.println(i+1 +". "+top5Sales.get(i).getTitle() +" \t\t\t(Sales:  "+ top5Sales.get(i).getGrossProfit()+")");
                       }
                       System.out.println("Choose a movie (Press 0 to exit): ");
                       input1 = sc.nextInt()-1;
                       if(input1 == -1){
                           break;
                       }
                       
                       top5Sales.get(input1).displayMovieDetails();
                       subMovieMenu(top5Sales.get(input1),apptype);
                   }while(input1 != -1);
                    break;
                case 2:
                    ArrayList<Movie> top5Tickets = new ArrayList<Movie>();
                    for(Map.Entry<String,Movie> entry : movies.entrySet()){
                        if(entry.getValue().getShowingStatus().equalsString("PREVIEW")||
                                entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                            top5Tickets.add(entry.getValue());
                        }
                    }
                    top5Tickets.sort(Comparator.comparingLong(Movie::getTicketsSold).reversed());
                    if(top5Tickets.size()==0){
                        System.out.println("No Available Movies.");
                        break;
                    } else if (top5Tickets.size() > 5) {
                    	numberToList = 5;
                    } else {
                    	numberToList = top5Tickets.size();
                    }
                    int input2;
                    do{
                        for(int i=0;i<numberToList;i++) {
                            System.out.println(i+1 +". "+top5Tickets.get(i).getTitle() +" \t\t\t(Tickets Sold:  "+ top5Tickets.get(i).getTicketsSold()+")");
                        }
                        System.out.println("Choose a movie (Press 0 to exit): ");
                        input2 = sc.nextInt()-1;
                        if(input2 == -1){
                            break;
                        }
                        
                        top5Tickets.get(input2).displayMovieDetails();

                        subMovieMenu(top5Tickets.get(input2),apptype);
                    }while(input2 != -1);
                    break;
                case 3:
                    ArrayList<Movie> top5Reviews = new ArrayList<Movie>();
                    for(Map.Entry<String,Movie> entry : movies.entrySet()){
                        if(entry.getValue().getShowingStatus().equalsString("PREVIEW")||
                                entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                            top5Reviews.add(entry.getValue());
                        }
                    }
                    for(int i=top5Reviews.size()-1;i>=0;i--){
                        if(top5Reviews.get(i).getReviews().size() <= 1){
                            top5Reviews.remove(i);
                        }
                    }
                    top5Reviews.sort(Comparator.comparingDouble(Movie::getAverageReviewScore).reversed());
                    if(top5Reviews.size()==0){
                        System.out.println("No Available Movies.");
                        break; 
                    } else if (top5Reviews.size() > 5) {
                    	numberToList = 5;
                    } else {
                    	numberToList = top5Reviews.size();
                    }
                    int input3;
                    do{
                        for(int i=0;i<numberToList;i++) {
                            System.out.println(i+1 +". "+top5Reviews.get(i).getTitle() +" \t\t\t(Review Score:  "+ top5Reviews.get(i).getAverageReviewScore()+")");
                        }
                        System.out.println("Choose a movie (Press 0 to exit): ");
                        input3 = sc.nextInt()-1;
                        if(input3 == -1){
                            break;
                        }
                        top5Reviews.get(input3).displayMovieDetails();
                        subMovieMenu(top5Reviews.get(input3),apptype);
                    }while(input3 != -1);
                    break;
                case 0:
                    System.out.println("Back to Customer App...");
                    break;
                default:
                    System.out.println("Please enter a number between 1-3.");
            }
        }while(choice!=0);

    }

*/

/*
    public void viewTop5Staff(){

        int choice;
        do{
            System.out.println(	"==================== View Top 5 Movies =====================\n" +
			                    "| 1. By Sales                                              |\n" +
			                    "| 2. By Tickets Sold                                       |\n" +
			                    "| 3. By Reviews                                            |\n" +
			                    "| 0. Back to StaffApp                                      |\n" +
			                    "===========================================================");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    ArrayList<Movie> top5Sales = new ArrayList<Movie>();
                    for(Map.Entry<String,Movie> entry : movies.entrySet()){
                        if(entry.getValue().getShowingStatus().equalsString("PREVIEW")||
                                entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                            top5Sales.add(entry.getValue());
                        }
                    }
                    top5Sales.sort(Comparator.comparingDouble(Movie::getGrossProfit).reversed());
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i + 1 + ". " + top5Sales.get(i).getTitle()+" \t\t\t(Sales:  "+ top5Sales.get(i).getGrossProfit()+")");
                    }
                    break;
                case 2:
                    ArrayList<Movie> top5Tickets = new ArrayList<Movie>();
                    for(Map.Entry<String,Movie> entry : movies.entrySet()){
                        if(entry.getValue().getShowingStatus().equalsString("PREVIEW")||
                                entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                            top5Tickets.add(entry.getValue());
                        }
                    }
                    top5Tickets.sort(Comparator.comparingLong(Movie::getTicketsSold).reversed());
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i + 1 + ". " + top5Tickets.get(i).getTitle()+" \t\t\t(Tickets Sold:  "+ top5Tickets.get(i).getTicketsSold()+")");
                    }
                    break;
                case 3:
                    ArrayList<Movie> top5Reviews = new ArrayList<Movie>();
                    for(Map.Entry<String,Movie> entry : movies.entrySet()){
                        if(entry.getValue().getShowingStatus().equalsString("PREVIEW")||
                                entry.getValue().getShowingStatus().equalsString("NOW_SHOWING")){
                            top5Reviews.add(entry.getValue());
                        }
                    }
                    for(int i=top5Reviews.size()-1;i>=0;i--){
                        if(top5Reviews.get(i).getReviews().size() <= 1){
                            top5Reviews.remove(i);
                        }
                    }
                    top5Reviews.sort(Comparator.comparingDouble(Movie::getAverageReviewScore).reversed());
                    if(top5Reviews.size()==0){
                        System.out.println("No Available Movies.");
                        break;
                    }
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i + 1 + ". " + top5Reviews.get(i).getTitle()+" \t\t\t(Review Score:  "+ top5Reviews.get(i).getAverageReviewScore()+")");
                    }
                    break;
                case 0:
                    System.out.println("Back to StaffApp...");
                    break;
                default:
                    System.out.println("Please enter a number between 1-3.");
            }
        }while(choice!=0);
    }
    
*/

    public List<Movie> getMovies() {
        List<Movie> movieList = new ArrayList<>(movies.values());
        return movieList;
    }

    public void loadObject() {
        File folder = new File(ProjectRootPathFinder.findProjectRootPath() + "/data/movies");

        File[] listOfFiles = folder.listFiles();
        
        if(listOfFiles != null){
        	for(int i=0;i<listOfFiles.length;i++){
        		String filepath = listOfFiles[i].getPath(); // Returns full path incl file name and type
        		Movie newMovie = (Movie)SerializerHelper.deSerializeObject(filepath);
                movies.put(newMovie.getMovieID(), newMovie);
            }
        }
    }

    public void saveObject(Movie movie) {
        String filepath = ProjectRootPathFinder.findProjectRootPath() + "/data/movies/movie_"+movie.getMovieID()+".dat";
        SerializerHelper.serializeObject(movie, filepath);
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
        System.out.println(movie.getTotalReviewScore());
        System.out.println(reviewScore);
        movie.addMovieReview(reviewID);
        movie.setAverageReviewScore(movie.getTotalReviewScore()/movie.getTotalReviewNo());
        this.saveObject(movie);
    }

    public void updateShowtimes(String movieID, String showtimeID) {
        this.movies.get(movieID).addShowtimeID(showtimeID);
    }
}
