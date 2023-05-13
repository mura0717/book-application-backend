package dat3.book_app.dto.reviews.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDeleteRequest {
    private String reviewId;
}
