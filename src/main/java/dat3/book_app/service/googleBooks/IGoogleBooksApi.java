package dat3.book_app.service.googleBooks;

import dat3.book_app.entity.googleBooks.GoogleBook;
import dat3.book_app.entity.bookRecommendations.BookRecommendation;

import java.util.List;

public interface IGoogleBooksApi {
    GoogleBook byReference(String bookReference);
    List<GoogleBook> fromReferences(List<String> references);

    List<GoogleBook> byAuthor(String author);
    List<GoogleBook> fromAiRecommendations(List<BookRecommendation> recommendations);

    List<GoogleBook> bySearch(String query);

    List<GoogleBook> slice();
}
