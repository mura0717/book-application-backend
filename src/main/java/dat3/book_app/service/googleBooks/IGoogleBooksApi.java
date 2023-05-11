package dat3.book_app.service.googleBooks;

import dat3.book_app.dto.books.BookDetailsResponse;
import dat3.book_app.dto.books.BookMinimalResponse;
import dat3.book_app.dto.books.pagination.BookPaginatedResponse;
import dat3.book_app.dto.books.recommendations.BookRecommendationResponse;
import dat3.book_app.dto.books.search.BookSearchResponse;
import dat3.book_app.entity.books.GoogleBook;
import dat3.book_app.entity.bookRecommendations.BookRecommendation;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IGoogleBooksApi {
    BookDetailsResponse fromReference(String bookReference);
    List<BookMinimalResponse> fromReferences(List<String> references);

    List<GoogleBook> byAuthor(String author);
    List<BookRecommendationResponse> fromAiRecommendations(List<BookRecommendation> recommendations);

    List<BookSearchResponse> bySearch(String query);

    List<BookPaginatedResponse> slice();
    List<BookPaginatedResponse> sliceWithGenre(String genre);
    HashMap<String, String> availableGenres();
}
