import java.util.Scanner;

public class CustomerApp {
    public static void main(String[] args) {
        // App Interface
        System.out.println("==================== MOBLIMA APP ====================\n" +
                "1. List Movies\n" +
                "2. Search Movie by Title\n" +
                "3. View Booking History\n" +
                "4. List Top 5 Movies by Ticket Sales\n" +
                "5. List Top 5 Movies by Review Score\n" +
                "6. Exit App\n");


        //Instantiate Objects
        MovieManager mm = MovieManager.getInstance();

        Scanner sc = new Scanner(System.in);
        int input,choice;
        String searchterm;
        // Loop for App
        do{
            System.out.println("Enter the number of your choice(1-6): ");
            input = sc.nextInt();
            switch(input){
                case 1:
                    for(int i=0;i<mm.getMovies().size();i++){
                        System.out.println(i+1+". "+mm.getMovies().get(i));
                    }
                    do{
                        System.out.println("Enter choice of movie(1-" + mm.getMovies().size()+": ");
                        choice = sc.nextInt();

                    }while(choice <1 || choice>mm.getMovies().size());
                    mm.displayMovieDetails(mm.getMovies().get(choice-1));
                    break;

                case 2:
                    System.out.println("Enter search term: ");
                    searchterm = sc.next();
                    if(mm.searchMovies(searchterm).size() == 0){
                        System.out.println("No Movies found.");
                        break;
                    }
                    for(int i=0;i<mm.searchMovies(searchterm).size();i++){
                        System.out.println(i+1+". "+mm.searchMovies(searchterm).get(i));
                    }
                    do{
                        System.out.println("Enter choice of movie(1-" + mm.searchMovies(searchterm).size()+": ");
                        choice = sc.nextInt();

                    }while(choice <1 || choice>mm.searchMovies(searchterm).size());
                    mm.displayMovieDetails(mm.searchMovies(searchterm).get(choice-1));
                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;
            }
        }while(input != 6);

    }
}
