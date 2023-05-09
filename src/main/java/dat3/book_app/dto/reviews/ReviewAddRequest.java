package dat3.book_app.dto.reviews;

import dat3.book_app.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewAddRequest {
    private String bookReference;
    private String review;
    private int rating;

    public Review toReview(){
        var reviewEntity = new Review();
        reviewEntity.setBookReference(bookReference);
        reviewEntity.setStars(rating);
        reviewEntity.setComment(review);
        return reviewEntity;
    }
}
