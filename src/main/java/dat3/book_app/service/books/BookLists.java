package dat3.book_app.service.books;

import dat3.book_app.dto.books.BookListResponse;

import java.util.List;

public interface BookLists {
    List<BookListResponse> bookLists(String username);
}
