package dat3.book_app.api;

import dat3.book_app.dto.books.BookDetailsResponse;
import dat3.book_app.dto.books.recommendations.BookRecommendationResponse;
import dat3.book_app.entity.books.GoogleBook;
import dat3.book_app.service.googleBooks.IGoogleBooksApi;
import dat3.book_app.service.openAI.AIBookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {
    private final IGoogleBooksApi googleBooks;
    private final AIBookService aiBookService;

    public BookController(IGoogleBooksApi googleBooks, AIBookService aiBookService) {
        this.googleBooks = googleBooks;
        this.aiBookService = aiBookService;
    }

    @GetMapping("author")
    public List<GoogleBook> books(String author){
        return googleBooks.byAuthor(author);
    }

    @GetMapping("reference")
    public BookDetailsResponse book(String reference){
        return googleBooks.fromReference(reference);
    }

    @GetMapping("search")
    public List<GoogleBook> searchedBooks(@RequestParam String query) {
        return googleBooks.bySearch(query);
    }

    @GetMapping("slice")
    public List<GoogleBook> slicedBooks(@RequestParam Optional<String> genre) {
        if (genre.isPresent()) {
            String genreStr = genre.get();
            return googleBooks.sliceWithGenre(genreStr);
        }
        return googleBooks.slice();
    }

    @GetMapping("recommendations")
    public List<BookRecommendationResponse> recommended(String author, String title){
        var aiResponse = aiBookService.recommendations(author,title,5);
        return googleBooks.fromAiRecommendations(aiResponse);
    }
}
