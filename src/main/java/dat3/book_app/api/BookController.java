package dat3.book_app.api;

import dat3.book_app.dto.books.BookListFullResponse;
import dat3.book_app.dto.books.BookListMinimumResponse;
import dat3.book_app.dto.books.BookListUpdateRequest;
import dat3.book_app.dto.googleBooks.BookDetailsResponse;
import dat3.book_app.dto.googleBooks.BookMinimalResponse;
import dat3.book_app.dto.googleBooks.recommendations.BookRecommendationResponse;
import dat3.book_app.entity.googleBooks.GoogleBook;
import dat3.book_app.service.bookLists.BookLists;
import dat3.book_app.service.googleBooks.IGoogleBooksApi;
import dat3.book_app.service.openAI.AIBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<GoogleBook> books(String author){
        return googleBooks.byAuthor(author);
    }

    @GetMapping("reference")
    public BookDetailsResponse book(String reference){
        return googleBooks.fromReference(reference);
    }

    @GetMapping("references")
    public List<BookMinimalResponse> books(List<String> references){
        return googleBooks.fromReferences(references);
    }

    @GetMapping("search")
    public List<GoogleBook> searchedBooks(@RequestParam String query) {
        return googleBooks.bySearch(query);
    }

    @GetMapping("slice")
    public List<GoogleBook> slicedBooks(@RequestParam Optional<String> filter) {
        if (filter.isPresent()) {
            String filterStr = filter.get();
            return googleBooks.sliceWithFilter(filterStr);
        }
        return googleBooks.slice();
    }

    @PatchMapping("updateList")
     public ResponseEntity<String> updateBookList(@RequestBody BookListUpdateRequest request){
        return bookLists.Update(request);
    }

    @GetMapping("bookLists")
    public List<BookListMinimumResponse> bookLists(Principal principal){
        if(principal == null)
            return new ArrayList<>();
        return bookLists.bookLists(principal.getName());
    }

    public BookListFullResponse bookList(String id){
        var bookList = bookLists.bookList(id);
        var books = googleBooks.fromReferences(bookList.getBookReferences());
        return new BookListFullResponse(bookList,books);
    }

    @GetMapping("recommendations")
    public List<BookRecommendationResponse> recommended(String author, String title){
        var aiResponse = aiBookService.recommendations(author,title,5);
        return googleBooks.fromAiRecommendations(aiResponse);
    }
}
