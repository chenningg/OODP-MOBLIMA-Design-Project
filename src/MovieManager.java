import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class MovieManager {
    //Variables
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    Scanner sc = new Scanner(System.in);
    Movie m = new Movie();

    //Singleton
    private static MovieManager single_instance = null;

    //Constructor
    private MovieManager() {}

    public static MovieManager getInstance()
    {
        if (single_instance == null)
            single_instance = new MovieManager();
        return single_instance;
    }


    public void viewTop5Cust(){
        System.out.println("==================== View Top 5 Movies =====================\n" +
                           "| 1. By Sales                                              |\n" +
                           "| 2. By Tickets Sold                                       |\n" +
                           "| 3. By Reviews                                            |\n" +
                           "| 4. Back                                                  |\n" +
                           "===========================================================");

        int choice = sc.nextInt();
        switch (choice){
            case 1:
                ArrayList<Movie> top5Sales = new ArrayList<Movie>(movies);
                top5Sales.sort(Comparator.comparingDouble(Movie::getGrossProfit).reversed());
                for(int i=0;i<5;i++) {
                    System.out.println(i+1 +". "+top5Sales.get(i).getTitle());
                }
                System.out.println("Choose a movie:");
                int input1 = sc.nextInt();
                displayMovieDetails(top5Sales.get(input1-1));
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
                break;
            case 3:
                ArrayList<Movie> top5Reviews = new ArrayList<Movie>(movies);
                top5Reviews.sort(Comparator.comparingDouble(Movie::getAverageReviewScore).reversed());
                for(int i=0;i<5;i++) {
                    System.out.println(i+1 +". "+top5Reviews.get(i).getTitle());
                }
                System.out.println("Choose a movie:");
                int input3 = sc.nextInt();
                displayMovieDetails(top5Reviews.get(input3-1));
                break;
            case 4:
               //CALL CUST MENU FOR CUST

        }
    }

    public void viewTop5Staff(){
        System.out.println("==================== View Top 5 Movies =====================\n" +
                "| 1. By Sales                                              |\n" +
                "| 2. By Tickets Sold                                       |\n" +
                "| 3. By Reviews                                            |\n" +
                "| 4. Back                                                  |\n" +
                "===========================================================");

        int choice = sc.nextInt();
        switch (choice){
            case 1:
                ArrayList<Movie> top5Sales = new ArrayList<Movie>(movies);
                top5Sales.sort(Comparator.comparingDouble(Movie::getGrossProfit).reversed());
                for(int i=0;i<5;i++) {
                    System.out.println(i+1 +". "+top5Sales.get(i).getTitle());
                }
                break;
            case 2:
                ArrayList<Movie> top5Tickets = new ArrayList<Movie>(movies);
                top5Tickets.sort(Comparator.comparingLong(Movie::getTicketsSold).reversed());
                for(int i=0;i<5;i++) {
                    System.out.println(i+1 +". "+top5Tickets.get(i).getTitle());
                }
                break;
            case 3:
                ArrayList<Movie> top5Reviews = new ArrayList<Movie>(movies);
                top5Reviews.sort(Comparator.comparingDouble(Movie::getAverageReviewScore).reversed());
                for(int i=0;i<5;i++) {
                    System.out.println(i+1 +". "+top5Reviews.get(i).getTitle());
                }
                break;
            case 4:
                StaffApp sa = StaffApp.getInstance();
                sa.staffMenu();
        }
    }

    public void displayMovies(){
        System.out.println("========================= Movies ===========================\n" +
                           "| 1. Now Showing                                           |\n" +
                           "| 2. Coming Soon                                           |\n" +
                           "| 3. Cineplexes                                            |\n" +
                           "| 4. Search by Movie Title                                 |\n" +
                           "| 5. Back                                                  |\n" +
                           "===========================================================");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice){
            case 1:

                ArrayList<Movie> nowShowing = new ArrayList<Movie>();
                for(int i=0;i<movies.size();i++){
                    if(movies.get(i).getShowingStatus().equalsString("NOW_SHOWING")){
                        nowShowing.add(movies.get(i));
                    }
                }
                for(int i=0;i<nowShowing.size();i++){
                    System.out.println(i+1 +". "+nowShowing.get(i).getTitle());
                }
                System.out.println("Choose a movie:");
                int option1 = sc.nextInt();
                displayMovieDetails(nowShowing.get(option1-1));
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
                displayMovieDetails(comingSoon.get(option2-1));
                break;
            case 3:
                //TO DO
                //LIST ALL CINEPLEXES
                //SELECT 1-3 TO SELECT CINEPLEX
                //LIST OUT ALL MOVIES IN CINEPLEX
                //WHEN MOVIE IS SELECTED
                //WHEN MOVIE IS SELECTED
                displayMovieDetails();
                break;
            case 4:
                String title = sc.next();
                searchMovies(title);
                break;
            case 5:
                //TO DO
                //CALL Customer App
                break;
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

    public void addMovie() {
        Movie newMovie = new Movie();
        List<Genre> genreList = new ArrayList<>();
        List<String> castList = new ArrayList<>();
        List<MovieFormat> formatList = new ArrayList<>();

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
        newMovie.setMovieDuration(sc.nextFloat());

        System.out.println("Enter showing status: ");
        String showStatus = sc.next();
        newMovie.setShowingStatus(ShowingStatus.valueOf(showStatus));

        System.out.println("Enter release date (format DD/MM/YYYY): ");
        String releaseDate = sc.next();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        LocalDate date = LocalDate.parse(releaseDate, dateFormat);
        newMovie.setReleaseDate(date);

        movies.add(newMovie);
    }

    public void editMovie() {
        System.out.println("Enter movieID: ");
        String movieID = sc.next();
        for (Movie movie : movies) {
            if (movie.getMovieID().equalsIgnoreCase(movieID)) {
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
                            List<Genre> Genres = new ArrayList<>();
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
                            List<String> newCastList = new ArrayList<>();
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
                            List<MovieFormat> newFormats = new ArrayList<>();
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
                            float newDuration = sc.nextFloat();
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
                } while (choice < 12);
                break;
            }
        }
    }

    public void deleteMovie() {
        System.out.println("Enter movieID to be deleted: ");
        String delMovieID = sc.next();
        for (Movie movie : movies)
            if (movie.getMovieID().equalsIgnoreCase(delMovieID)) {
                movies.remove(movie);
                System.out.println("Movie deleted");
                break;
            }
    }

    public void peekMovie() {
        System.out.println("Last movie added was: " + movies.get(movies.size()-1).getTitle());
    }

    public List<Movie> getMovies() {
        return movies;
    }


}

// TODO TEST
