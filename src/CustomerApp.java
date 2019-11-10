import java.util.Scanner;

public class CustomerApp {
    public static void main(String[] args) {
        // App Interface
        System.out.println("==================== MOBLIMA APP ====================\n" +
                "1. View movie listings \n" +
                "2. Leave review\n" +
                "3. View top 5\n" +
                "4. Check booking history\n" +
                "5. Quit\n");


        //Instantiate Objects
        MovieManager mm = MovieManager.getInstance();
        ReviewManager rm= ReviewManager.getInstance();
        CustomerManager cm= CustomerManager.getInstance();

        Scanner sc = new Scanner(System.in);
        int input,choice,emailOrMobile;
        String searchterm;
        // Loop for App
        do{
            System.out.println("Enter the number of your choice(1-5): ");
            input = sc.nextInt();
            switch(input){
                case 1://view movie listings
                	mm.displayMovies();
                    break;

                case 2://leave review
                    System.out.println("Enter search term: ");
                    searchterm = sc.next();
                    //search for movie using searchterm
                    if(mm.searchMovies(searchterm).size() == 0){
                        System.out.println("No Movies found.");
                        break;
                    }
                    //print out movies containing searchterm
                    for(int i=0;i<mm.searchMovies(searchterm).size();i++){
                        System.out.println(i+1+". "+mm.searchMovies(searchterm).get(i));
                    }
                    //let user choose movie
                    do{
                        System.out.println("Enter choice of movie(1-" + mm.searchMovies(searchterm).size()+": ");
                        choice = sc.nextInt();

                    }while(choice <1 || choice>mm.searchMovies(searchterm).size());
                    rm.createReview(mm.searchMovies(searchterm).get(choice-1));
                    break;

                case 3://view top 5
                	mm.viewTop5Cust();
                    break;

                case 4://check booking history
                	do {
	                	System.out.print("Enter 1,2 or 3: \n"
	                			+ "1. Check via email address\n"
	                			+ "2. Check via mobile number\n"
	                			+ "3. Return to previous");
	                	emailOrMobile= sc.nextInt();
	                	switch (emailOrMobile) {
	                	case 1:
	                		cm.printPastBookingByEmail();
	                		break;
	                	case 2:
	                		cm.printPastBookingByMobile();
	                		break;
                		default: System.out.println("Please enter a valid choice");
	                	}
                	}while(emailOrMobile!=3);
                	
                    break;
            }
        }while(input != 5);
        sc.close();

    }
}
