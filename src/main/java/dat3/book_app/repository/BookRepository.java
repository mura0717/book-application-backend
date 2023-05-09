package dat3.book_app.repository;

import dat3.book_app.entity.bookLists.Booklist;
import dat3.book_app.entity.books.GoogleBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<GoogleBook, String> {
}
