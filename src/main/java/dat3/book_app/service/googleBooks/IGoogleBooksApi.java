package dat3.book_app.service.googleBooks;

import dat3.book_app.dto.googleBooks.BookResponse;

import java.util.List;

public interface IGoogleBooksApi {
    BookResponse byReference(String bookReference);

    List<BookResponse> byAuthor(String author);

    List<BookResponse> bySearch(String query);

    List<BookResponse> slice();
}
