package dat3.book_app.service.reviews;

import dat3.book_app.dto.reviews.ReviewAddRequest;
import dat3.book_app.dto.reviews.ReviewResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookReviews {
    List<ReviewResponse> reviews(String bookReference);
    ReviewResponse addReview(ReviewAddRequest request, String username);
}
