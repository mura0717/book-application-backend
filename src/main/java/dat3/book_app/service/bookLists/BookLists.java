package dat3.book_app.service.bookLists;

import dat3.book_app.dto.bookLists.request.BookListCreateRequest;
import dat3.book_app.dto.bookLists.request.BookListUpdateRequest;
import dat3.book_app.dto.bookLists.response.BookListWithBooks;
import dat3.book_app.dto.bookLists.response.BookListWithReferences;
import dat3.book_app.dto.bookLists.response.BookListUpdateResponse;
import dat3.book_app.dto.bookLists.response.BookListsTitleResponse;

import java.util.List;

public interface BookLists {
    List<BookListWithReferences> getBookListWithReferences(String username);
    List<BookListsTitleResponse> getBookListWithTitles(String username);
    BookListWithBooks getBookListWithBooks(String id);
    BookListUpdateResponse addToBookList(BookListUpdateRequest request);
    BookListsTitleResponse createBookList(BookListCreateRequest request, String username);
    boolean bookAlreadyAdded(String bookListId, String bookReference);

    BookListUpdateResponse removeBookList(String bookListId);

    BookListUpdateResponse editBookList(BookListUpdateRequest request, String bookListId);

    BookListUpdateResponse removeFromBookList(BookListUpdateRequest request);
}
