package dat3.book_app.service.googleBooks;

import dat3.book_app.dto.googleBooks.BookResponse;
import dat3.book_app.entity.bookRecommendations.BookRecommendation;

import java.util.List;

public interface IGoogleBooksApi {
    BookResponse byReference(String bookReference);

    List<BookResponse> byAuthor(String author);
    List<BookResponse> fromAiRecommendations(List<BookRecommendation> recommendations);

    List<BookResponse> bySearch(String query);

    List<BookResponse> slice();
}
