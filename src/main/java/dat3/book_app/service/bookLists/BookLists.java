package dat3.book_app.service.bookLists;

import dat3.book_app.dto.bookLists.BookListCreateRequest;
import dat3.book_app.dto.bookLists.BookListMinimumResponse;
import dat3.book_app.dto.bookLists.BookReferencesTitleRespons;
import dat3.book_app.dto.bookLists.BookListUpdateRequest;
import dat3.book_app.entity.bookLists.Booklist;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookLists {
    List<BookListMinimumResponse> bookLists(String username);
    List<BookReferencesTitleRespons> listTitles(String username);
    Booklist bookList(String id);
    ResponseEntity<String> Update(BookListUpdateRequest request);

    BookReferencesTitleRespons create(BookListCreateRequest request, String username);
}
