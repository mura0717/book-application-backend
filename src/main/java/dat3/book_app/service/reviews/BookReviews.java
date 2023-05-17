package dat3.book_app.service.reviews;

import dat3.book_app.dto.reviews.requests.ReviewUpdateRequest;
import dat3.book_app.dto.reviews.responses.ReviewUpdateResponse;

import java.security.Principal;
import java.util.List;

public interface BookReviews {
    List<ReviewUpdateResponse> unrestrictedReviews(String bookReference, Principal principal);
    List<ReviewUpdateResponse> restrictedReviews(String bookReference);
    ReviewUpdateResponse addReview(ReviewUpdateRequest request, String username);
    boolean removeReview(String reviewId, String username);
}
