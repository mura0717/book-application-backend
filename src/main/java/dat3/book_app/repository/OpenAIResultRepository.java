package dat3.book_app.repository;

import dat3.book_app.entity.OpenAIResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenAIResultRepository extends JpaRepository<OpenAIResult, String> {
}
