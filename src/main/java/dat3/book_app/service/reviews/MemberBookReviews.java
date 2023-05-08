package dat3.book_app.service.reviews;

import dat3.book_app.dto.reviews.ReviewAddRequest;
import dat3.book_app.dto.reviews.ReviewResponse;
import dat3.book_app.repository.ReviewRepository;
import dat3.security.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Service
public class MemberBookReviews implements BookReviews {
    private final MemberRepository _memberRepository;
    private final ReviewRepository _reviewRepository;

    public MemberBookReviews(MemberRepository repository, ReviewRepository reviewRepository) {
        _memberRepository = repository;
        _reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewResponse> reviews(String bookReference) {
        return _reviewRepository
                .findByBookReferenceLike(bookReference)
                .stream().map(ReviewResponse::fromReview)
                .toList();

    }

    @Override
    public ReviewResponse addReview(ReviewAddRequest request, String username){
        var member = _memberRepository.findByUsernameLike(username).orElse(null);
        if(member == null)
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND,"Member not found");
        var reviewEntity = request.toReview();
        member.getReviews().add(reviewEntity);
        _memberRepository.save(member);
        return ReviewResponse.fromReview(reviewEntity);
    }
}
