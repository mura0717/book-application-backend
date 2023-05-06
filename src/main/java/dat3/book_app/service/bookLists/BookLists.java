package dat3.book_app.service.bookLists;

import dat3.book_app.dto.books.BookListResponse;
import dat3.book_app.dto.books.BookListUpdateRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookLists {
    List<BookListResponse> bookLists(String username);
    ResponseEntity<String> Update(BookListUpdateRequest request);
}
