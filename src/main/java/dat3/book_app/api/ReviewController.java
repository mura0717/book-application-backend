package dat3.book_app.api;

import dat3.book_app.dto.reviews.ReviewAddRequest;
import dat3.book_app.dto.reviews.ReviewResponse;
import dat3.book_app.service.reviews.BookReviews;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class ReviewController {
    private final BookReviews _bookReviews;

    public ReviewController(BookReviews bookReviews) {
        _bookReviews = bookReviews;
    }

    public ReviewResponse add(ReviewAddRequest request, Principal principal){
        return _bookReviews.addReview(request,principal.getName());
    }

    @GetMapping("bookReviews")
    public List<ReviewResponse> all(String bookReference){
        return _bookReviews.reviews(bookReference);
    }
}
