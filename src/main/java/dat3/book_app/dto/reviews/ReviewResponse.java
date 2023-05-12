package dat3.book_app.dto.reviews;

import dat3.book_app.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponse {
    private String reviewId;
    private String review;
    private int rating;
    private String username;
    private boolean editable;
    private String bookReference;

    public static ReviewResponse fromReview(Review reviewEntity){
        var response = new ReviewResponse();
        response.review  = reviewEntity.getComment();
        response.rating = reviewEntity.getStars();
        response.username = reviewEntity.getMember().getUsername();
        response.reviewId = reviewEntity.getId();
        response.bookReference = reviewEntity.getBookReference();
        return response;
    }
}
