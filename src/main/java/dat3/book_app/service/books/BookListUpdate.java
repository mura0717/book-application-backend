package dat3.book_app.service.books;

import dat3.book_app.dto.books.BookListUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface BookListUpdate {
    ResponseEntity<String> Update(BookListUpdateRequest request);
}
