import java.util.List;
import java.util.Scanner;

public class ReviewManager {
    Scanner sc = new Scanner(System.in);
    private Review review = null;
    private static ReviewManager single_instance = null;

    private ReviewManager() {}

    public static ReviewManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ReviewManager();
        return single_instance;
    }

    public void createReview(Movie movie) {
        Review submittedReview = new Review();
        int choice;
        do {
            System.out.println("(1) Enter your name: ");
            System.out.println("(2) Enter title of review: ");
            System.out.println("(3) Enter review text: ");
            System.out.println("(4) Enter score: ");
            System.out.println("(5) Submit review");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // TODO: Name or ID?
                    System.out.println("Enter here: ");
                    String reviewerName = sc.next();
                    submittedReview.setReviewerID(reviewerName);
                    break;
                case 2:
                    System.out.println("Enter here: ");
                    String reviewTitle = sc.next();
                    submittedReview.setReviewTitle();
                    break;
                case 3:
                    System.out.println("Enter here: ");
                    String reviewBody = sc.next();
                    submittedReview.setReviewBody(reviewBody);
                    break;
                case 4:
                    System.out.println("Enter score of 1-5: ");
                    float reviewScore = sc.nextFloat();
                    submittedReview.setScore(reviewScore);
                    break;
                case 5:
                    System.out.println("Review submitted!");
                    movie.appendReview(submittedReview);
                    break;
                if (choice == 5)
                    break;
            }
        } while (choice < 6);
    }

    public List<Review> getReviews(Movie movie) {}

    public float getAverageReviewScore(String movieID) {}

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
