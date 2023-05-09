package dat3.book_app.repository;

import dat3.book_app.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findByBookReferenceLike(@NonNull String bookReference);
}
