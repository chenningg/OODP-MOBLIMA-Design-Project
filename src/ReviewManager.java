import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReviewManager {
	// Attributes
    private Scanner sc = new Scanner(System.in);
    private Map<String, Review> reviews;

    private static ReviewManager single_instance = null;

    private ReviewManager() {
    	this.reviews = new HashMap<String, Review>();
    	this.reviews = this.load();
    }

    public static ReviewManager getInstance() {
        if (single_instance == null)
            single_instance = new ReviewManager();
        return single_instance;
    }
    
    
	// Public exposed methods to app
    public void reviewMenu(String movieID) {
    	int choice;
    	
    	do {
            System.out.println(	"===================== REVIEW PORTAL =====================\n" +
			                    "| 1. Leave a review	 						    	 |\n" +
				                "| 0. Back to Movie Listings	                         |\n" +
				                "=========================================================");    	
    	
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
	            case 1:
	            	this.addReview(movieID);
	            	choice = 0;
	            	break;
	            case 0:
	            	System.out.println("Back to Movie Listings......");
	            	break;
            	default:
            		System.out.println("Invalid choice. Please enter a number between 0-1");
            }
            
    	} while (choice != 0);
    }
    
    public void printReviews(List<String> reviewIDs) {
    	int i=1;
    	
    	for (String reviewID : reviewIDs) {
    		Review review = this.reviews.get(reviewID);
    		
    		System.out.println("================ REVIEW " + i + " ================");
            System.out.println("Your current review: ");
            System.out.println("Name: " + review.getReviewerName());
            System.out.println("Title: " + review.getReviewTitle() + "     Score: " + review.getScore() + "/5");
            System.out.println("Review body: " + review.getReviewBody());
            System.out.println("");
            
            i++;
    	}
    	
    }
    
    
	// Private CRUD methods    
    private void addReview(String movieID) {
    	Review review = new Review();
    	
    	////////////// INPUT VALIDATION NEEDED
 	
        System.out.println("Enter your name: ");
        review.setReviewerName(sc.next());
        
        System.out.println("Enter title of review: ");
        review.setReviewTitle(sc.next());
        
        System.out.println("Enter review: ");
        review.setReviewBody(sc.next());
        
        System.out.println("Enter a movie score between 0-5: ");
        review.setScore(sc.nextDouble());
        
        int choice;
        
        do {
            System.out.println(	"========================= ADD REVIEW ====================\n" +
			                    "| 1. Submit review	   						    	 	 |\n" +
			                    "| 2. Edit review	   						    	 	 |\n" +
				                "| 0. Discard review, back to ReviewPortal               |\n" +
				                "=========================================================");            	
            
            System.out.println("Your current review: ");
            System.out.println("Name: " + review.getReviewerName());
            System.out.println("Title: " + review.getReviewTitle());
            System.out.println("Review body: " + review.getReviewBody());
            System.out.println("Score: " + review.getScore());
            
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
        
            switch (choice) {
            case 1:
            	String reviewID = IDHelper.getLatestID("review");
            	review.setReviewID(reviewID);
            	this.save(review);
            	MovieManager.getInstance().updateReview(movieID, reviewID, review.getScore());
            	
            	this.reviews.put(review.getReviewID(), review);
            	
            	System.out.println("Review created! Back to ReviewPortal......");
            	break;
            case 2:
            	this.editReview(review);
            	break;
            case 0:
            	System.out.println("Review discarded. Back to ReviewPortal......");
            	break;
        	default:
        		System.out.println("Invalid choice. Please enter a number between 0-2");
        		break;
            }
        
        } while (choice != 0);
    }
    
    private void editReview(Review review) {
    	int choice;
    	
    	do {
            System.out.println(	"======================== EDIT REVIEW ====================\n" +
				                "| 1. Edit Name		   						    	 	 |\n" +
				                "| 2. Edit Title	   						    	 	 |\n" +
				                "| 3. Edit Review Body	   						    	 |\n" +
				                "| 4. Edit Score	   						    	 	 |\n" +			                
				                "| 0. Finish Editing Review 				             |\n" +
				                "=========================================================");    		

            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
            case 1:
                System.out.println("Enter your name: ");
                review.setReviewerName(sc.next());
            	break;
            case 2:
                System.out.println("Enter title of review: ");
                review.setReviewTitle(sc.next());
            	break;
            case 3:
                System.out.println("Enter review: ");
                review.setReviewBody(sc.next());
            	break;
            case 4: 
                System.out.println("Enter a movie score between 0-5: ");
                review.setScore(sc.nextDouble());   
            	break;
            case 0:
            	System.out.println("Review discarded. Back to AddReview......");
            	break;
        	default:
        		System.out.println("Invalid choice. Please enter a number between 0-4");
        		break;
            } 
    	} while (choice != 0);       
    }
    
    
    
    
	// Private Serialization and Deserialization
	private void save(Review review) {
		String filePath = ProjectRootPathFinder.findProjectRootPath() + "/data/reviews/" + review.getReviewID() + ".dat";
		SerializerHelper.serializeObject(review, filePath);
	}
    
    public HashMap<String,Review> load() {
        HashMap<String, Review> loadedReviews = new HashMap<String, Review>();
        File folder = new File(ProjectRootPathFinder.findProjectRootPath() + "/data/reviews");

        File[] listOfFiles = folder.listFiles();
        
        if(listOfFiles != null){
          for(int i=0;i<listOfFiles.length;i++){
            String filepath = listOfFiles[i].getPath(); // Returns full path incl file name and type
            Review newReview = (Review)SerializerHelper.deSerializeObject(filepath);
            String fileID = listOfFiles[i].getName().split("\\.(?=[^\\.]+$)")[0].split("_")[1];
                loadedReviews.put(fileID, newReview);
            }
        }
        return loadedReviews;
    }    
}

