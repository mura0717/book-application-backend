package dat3.book_app.service.openAI;

import dat3.book_app.entity.bookRecommendations.BookRecommendation;

import java.util.List;

public interface AIBookService {
    String bookSummary(String author, String title, int length);

    List<BookRecommendation> recommendations(String description, int maxResults);

    List<BookRecommendation> recommendations(String author, String title, int maxResults);
}
