package dat3.book_app.service.googleBooks;

import dat3.book_app.dto.googleBooks.BookResponse;
import dat3.book_app.dto.googleBooks.GoogleBooksAPIResponse;

import java.util.List;

public interface IGoogleBooksApi {
    BookResponse byReference(String bookReference);

    List<BookResponse> byAuthor(String author);

    List<BookResponse> asSearch(String query);

    List<BookResponse> paginated(String startIndex);
}
