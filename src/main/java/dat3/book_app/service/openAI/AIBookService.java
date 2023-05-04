package dat3.book_app.service.openAI;

import dat3.book_app.entity.bookRecommendations.BookRecommendation;

import java.util.List;

public interface AIBookService {
    String bookSummary(String author, String title, int length);

    List<BookRecommendation> recommendedBooks(String description, int maxResults);

    List<BookRecommendation> recommendedBooks(String author, String title, int maxResults);
}
