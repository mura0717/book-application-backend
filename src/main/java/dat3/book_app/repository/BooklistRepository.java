package dat3.book_app.repository;

import dat3.book_app.entity.bookLists.Booklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooklistRepository extends JpaRepository<Booklist, String> {
    boolean existsByTitleLike(String title);
    List<Booklist> findByMember_UsernameLike(@NonNull String username);
}
