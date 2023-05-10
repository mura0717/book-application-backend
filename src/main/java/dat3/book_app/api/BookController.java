package dat3.book_app.api;

import dat3.book_app.dto.bookReferences.BookReferenceResponse;
import dat3.book_app.dto.books.BookDetailsResponse;
import dat3.book_app.dto.books.recommendations.BookRecommendationResponse;
import dat3.book_app.entity.bookLists.Booklist;
import dat3.book_app.entity.books.GoogleBook;
import dat3.book_app.repository.BooklistRepository;
import dat3.book_app.service.bookReferences.BookReferencesService;
import dat3.book_app.service.googleBooks.IGoogleBooksApi;
import dat3.book_app.service.openAI.AIBookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {
    private final IGoogleBooksApi googleBooks;
    private final AIBookService aiBookService;

    private final BookReferencesService bookReferencesService;

    private final BooklistRepository booklistRepository;

    public BookController(IGoogleBooksApi googleBooks, AIBookService aiBookService, BookReferencesService bookReferencesService, BooklistRepository booklistRepository) {
        this.googleBooks = googleBooks;
        this.aiBookService = aiBookService;
        this.bookReferencesService = bookReferencesService;
        this.booklistRepository = booklistRepository;
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
        if (genre.isPresent())
            return googleBooks.sliceWithGenre(genre.get());
        return googleBooks.slice();
    }
    @GetMapping("recommendations")
    public List<BookRecommendationResponse> recommended(String author, String title){
        var aiResponse = aiBookService.recommendations(author,title,5);
        return googleBooks.fromAiRecommendations(aiResponse);
    }

    @GetMapping("references")
    public List<BookReferenceResponse> references(@RequestParam("booklistId") String booklistId) {
        Booklist booklist = booklistRepository.findById(booklistId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booklist not found"));
        List<String> references = booklist.getBookReferences();
        return bookReferencesService.getBookReferences(references);
    }

}
