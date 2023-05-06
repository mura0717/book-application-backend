package dat3.book_app.service.bookLists;

import dat3.book_app.dto.books.BookListMinimumResponse;
import dat3.book_app.dto.books.BookListUpdateRequest;
import dat3.book_app.entity.Booklist;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookLists {
    List<BookListMinimumResponse> bookLists(String username);
    Booklist bookList(String id);
    ResponseEntity<String> Update(BookListUpdateRequest request);
}
