package dat3.book_app.service.googleBooks;

import dat3.book_app.dto.bookLists.response.BookListBook;
import dat3.book_app.dto.books.BookDetailsResponse;
import dat3.book_app.dto.books.pagination.BookPaginatedResponse;
import dat3.book_app.dto.books.recommendations.BookRecommendationResponse;
import dat3.book_app.dto.books.search.BookSearchResponse;
import dat3.book_app.entity.books.GoogleBook;
import dat3.book_app.entity.bookRecommendations.BookRecommendation;

import java.util.HashMap;
import java.util.List;

public interface IGoogleBooksApi {
    BookDetailsResponse getBookByReference(String bookReference);
    List<BookListBook> getBooksByReferences(List<String> references);

    List<GoogleBook> getBooksByAuthor(String author);
    List<BookRecommendationResponse> fromAiRecommendations(List<BookRecommendation> recommendations);

    List<BookSearchResponse> bySearch(String query);

    List<BookPaginatedResponse> slice();
    List<BookPaginatedResponse> sliceWithGenre(String genre);
    HashMap<String, String> availableGenres();
}
