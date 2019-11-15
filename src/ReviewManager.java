import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReviewManager {
    // prevents errors
    Scanner sc = new Scanner(System.in);
    private Review review = null;
    private static ReviewManager single_instance = null;

    private ReviewManager() {
    }

    public static ReviewManager getInstance() {
        if (single_instance == null)
            single_instance = new ReviewManager();
        return single_instance;
    }

    public void createReview(Movie movie) {
        Review submittedReview = new Review();
        int choice;
        do {
            System.out.println("1. Enter your name: ");
            System.out.println("2. Enter title of review: ");
            System.out.println("3. Enter review text: ");
            System.out.println("4. Enter score: ");
            System.out.println("5. Submit review");
            System.out.println("0. Back");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter here: ");
                    String reviewTitle = sc.next();
                    submittedReview.setReviewTitle(reviewTitle);
                    break;
                case 2:
                    System.out.println("Enter here: ");
                    String reviewBody = sc.next();
                    submittedReview.setReviewBody(reviewBody);
                    break;
                case 3:
                    System.out.println("Enter score of 1-5: ");
                    float reviewScore = sc.nextFloat();
                    submittedReview.setScore(reviewScore);
                    break;
                case 4:
                    System.out.println("Review submitted!");
                    movie.addMovieReview(submittedReview);
                    break;
                default:
                    System.out.println("Please enter correct input!");
                    break;
            }
        } while (choice != 0);
    }


    public ArrayList<Review> getReviews(Movie movie) {
        return movie.getMovieReviews();
    }

    public float getAverageReviewScore(Movie movie) {
        ArrayList<Review> reviews = movie.getMovieReviews();
        float sum = 0;
        for (Review review : reviews) {
            sum += review.getScore();
        }
        return sum / reviews.size();
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }


    public void displayReview(Movie movie) {
        ArrayList<Review> reviewList = getReviews(movie);
        for (int i = 0; i < reviewList.size(); i++) {
            System.out.println(i + 1 + ". " + reviewList.get(i));
        }
    }
}