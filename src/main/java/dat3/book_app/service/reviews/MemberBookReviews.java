package dat3.book_app.service.reviews;

import dat3.book_app.dto.reviews.requests.ReviewUpdateRequest;
import dat3.book_app.dto.reviews.responses.ReviewRemoveResponse;
import dat3.book_app.dto.reviews.responses.ReviewUpdateResponse;
import dat3.book_app.entity.Review;
import dat3.book_app.repository.ReviewRepository;
import dat3.security.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class MemberBookReviews implements BookReviews {
    private final MemberRepository _memberRepository;
    private final ReviewRepository _reviewRepository;

    public MemberBookReviews(MemberRepository repository, ReviewRepository reviewRepository) {
        _memberRepository = repository;
        _reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewUpdateResponse> reviews(String bookReference, String username) {
        return _reviewRepository
                .findByBookReferenceLike(bookReference)
                .stream().map(review -> {
                    var editable = review.getMember().getUsername().equals(username);
                    return ReviewUpdateResponse.fromReview(review,editable);
                })
                .toList();

    }

    @Override
    public ReviewUpdateResponse addReview(ReviewUpdateRequest request, String username){
        var member = _memberRepository.findByUsernameLike(username).orElse(null);
        if(member == null)
            throw new HttpServerErrorException(NOT_FOUND,"Member not found");
        var updateCandidate = findUpdateCandidate(request,username);
        Review reviewEntity;
        if(updateCandidate == null)
            reviewEntity = request.toReview();
        else
            reviewEntity = request.toUpdatedReview(updateCandidate);
        reviewEntity.setMember(member);
        _reviewRepository.save(reviewEntity);
        return ReviewUpdateResponse.fromReview(reviewEntity,true);
    }

    @Override
    public ReviewRemoveResponse removeReview(String reviewId, String username) {
        var deleteCandidate = _reviewRepository.findById(reviewId).orElse(null);
        if(deleteCandidate == null)
            throw new HttpServerErrorException(NOT_FOUND,"Review not found");
        if(!deleteCandidate.getMember().getUsername().equals(username))
            throw new HttpServerErrorException(FORBIDDEN,"Not allowed");
        _reviewRepository.delete(deleteCandidate);
        return new ReviewRemoveResponse(true);
    }

    private Review findUpdateCandidate(ReviewUpdateRequest request, String username){
        var review = _reviewRepository.findById(request.getReviewId()).orElse(null);
        if(review == null)
            return null;
        var reviewUsername = review.getMember().getUsername();
        if(!username.equals(reviewUsername))
            throw new HttpServerErrorException(FORBIDDEN,"Unauthorized");
        return review;
    }
}
