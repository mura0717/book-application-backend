package dat3.book_app.repository;

import dat3.book_app.entity.Booklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooklistRepository extends JpaRepository<Booklist, String> {
}
