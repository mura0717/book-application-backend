package dat3.book_app.api;

import dat3.book_app.dto.books.BookListResponse;
import dat3.book_app.dto.books.BookListUpdateRequest;
import dat3.book_app.dto.googleBooks.BookResponse;
import dat3.book_app.dto.googleBooks.recommendations.BookRecommendationsResponse;
import dat3.book_app.service.bookLists.BookLists;
import dat3.book_app.service.googleBooks.IGoogleBooksApi;
import dat3.book_app.service.openAI.AIBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {
    private final BookLists bookLists;
    private final IGoogleBooksApi googleBooks;
    private final AIBookService aiBookService;

    public BookController(BookLists bookLists, IGoogleBooksApi googleBooks, AIBookService aiBookService) {
        this.bookLists = bookLists;
        this.googleBooks = googleBooks;
        this.aiBookService = aiBookService;
    }

    @GetMapping("author")
    public List<BookResponse> books(String author){
        return googleBooks.byAuthor(author);
    }

    @GetMapping("reference")
    public BookResponse book(String reference){
        return googleBooks.byReference(reference);
    }

    @GetMapping("search")
    public List<BookResponse> searchedBooks(@RequestParam String query) {
        return googleBooks.bySearch(query);
    }

    @GetMapping("slice")
    public List<BookResponse> slicedBooks() {
        return googleBooks.slice();
    }

    @PatchMapping("updateList")
     public ResponseEntity<String> updateBookList(@RequestBody BookListUpdateRequest request){
        return bookLists.Update(request);
    }

    @GetMapping("bookLists")
    public List<BookListResponse> bookLists(Principal principal){
        if(principal == null)
            return new ArrayList<>();
        return bookLists.bookLists(principal.getName());
    }

    @GetMapping("recommendations")
    public BookRecommendationsResponse recommended(String author, String title){
        var aiResponse = aiBookService.recommendations(author,title,5);
        var books = googleBooks.fromAiRecommendations(aiResponse);
        return new BookRecommendationsResponse(books);
    }
}
