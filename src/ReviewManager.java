import java.util.List;

public class ReviewManager {
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
