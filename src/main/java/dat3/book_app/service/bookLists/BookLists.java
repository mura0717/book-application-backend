package dat3.book_app.service.bookLists;

import dat3.book_app.dto.bookLists.request.BookListCreateRequest;
import dat3.book_app.dto.bookLists.request.BookListUpdateRequest;
import dat3.book_app.dto.bookLists.response.*;

import java.util.List;

public interface BookLists {
    List<BookListWithReferences> getBookListWithReferences(String username);
    List<BookListsTitleResponse> getBookListWithTitles(String username);
    BookListWithBooks getBookListWithBooks(String id);
    BookListUpdateResponse addToBookList(BookListUpdateRequest request);
    BookListCreateResponse createBookList(BookListCreateRequest request, String username);
    boolean bookAlreadyAdded(String bookListId, String bookReference);
}
