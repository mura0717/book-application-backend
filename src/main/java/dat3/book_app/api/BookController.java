package dat3.book_app.api;

import dat3.book_app.dto.books.BookListUpdateRequest;
import dat3.book_app.dto.googleBooks.BookResponse;
import dat3.book_app.service.books.BookListUpdate;
import dat3.book_app.service.googleBooks.IGoogleBooksApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {
    private final BookListUpdate bookUpdate;
    private final IGoogleBooksApi googleBooks;

    public BookController(BookListUpdate bookUpdate, IGoogleBooksApi googleBooks) {
        this.bookUpdate = bookUpdate;
        this.googleBooks = googleBooks;
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
        return googleBooks.asSearch(query);
    }

    @PatchMapping("update")
     public ResponseEntity<String> updateBookList(@RequestBody BookListUpdateRequest request){
        return bookUpdate.Update(request);
    }
}
