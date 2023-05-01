package dat3.book_app.api;

import dat3.book_app.dto.books.BookListUpdateRequest;
import dat3.book_app.service.books.IBookListUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {
    private final IBookListUpdate<BookListUpdateRequest> bookUpdate;

    public BookController(IBookListUpdate<BookListUpdateRequest> bookUpdate) {
        this.bookUpdate = bookUpdate;
    }

    @PatchMapping("update")
    public ResponseEntity<String> UpdateBookList(@RequestBody BookListUpdateRequest request){
        return bookUpdate.Update(request);
    }
}
