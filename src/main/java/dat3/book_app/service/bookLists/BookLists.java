package dat3.book_app.service.bookLists;

import dat3.book_app.dto.bookLists.request.BookListCreateRequest;
import dat3.book_app.dto.bookLists.request.BookListUpdateRequest;
import dat3.book_app.dto.bookLists.response.BookListMinimumResponse;
import dat3.book_app.dto.bookLists.response.BookListUpdateResponse;
import dat3.book_app.dto.bookLists.response.BookListsTitleResponse;
import dat3.book_app.entity.bookLists.Booklist;

import java.util.List;

public interface BookLists {
    List<BookListMinimumResponse> bookLists(String username);
    List<BookListsTitleResponse> listTitles(String username);
    Booklist bookList(String id);
    BookListUpdateResponse addToBookList(BookListUpdateRequest request);

    BookListsTitleResponse create(BookListCreateRequest request, String username);
}
