package dat3.book_app.api;

import dat3.book_app.dto.reviews.requests.ReviewDeleteRequest;
import dat3.book_app.dto.reviews.requests.ReviewUpdateRequest;
import dat3.book_app.dto.reviews.responses.ReviewUpdateResponse;
import dat3.book_app.service.reviews.BookReviews;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin
public class ReviewController {
    private final BookReviews _bookReviews;

    public ReviewController(BookReviews bookReviews) {
        _bookReviews = bookReviews;
    }

    @PostMapping("update")
    public ReviewUpdateResponse update(@RequestBody ReviewUpdateRequest request, Principal principal){
        return _bookReviews.addReview(request,principal.getName());
    }

    @GetMapping
    public List<ReviewUpdateResponse> all(String bookReference, Principal principal){
        return _bookReviews.reviews(bookReference,principal.getName());
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestBody ReviewDeleteRequest request, Principal principal){
        return _bookReviews.removeReview(request.getReviewId(),principal.getName());
    }
}
