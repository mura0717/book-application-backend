package dat3.book_app.repository;

import dat3.book_app.entity.Booklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooklistRepository extends JpaRepository<Booklist, String> {
    List<Booklist> findByUser_UsernameLike(String username);
}
