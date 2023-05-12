package dat3.book_app.service.reviews;

import dat3.book_app.dto.reviews.ReviewUpdateRequest;
import dat3.book_app.dto.reviews.ReviewResponse;

import java.util.List;

public interface BookReviews {
    List<ReviewResponse> reviews(String bookReference);
    ReviewResponse addReview(ReviewUpdateRequest request, String username);
}
