package dat3.book_app.api;

import dat3.book_app.dto.books.BookListUpdateRequest;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
    public ResponseEntity<String> UpdateBookList(BookListUpdateRequest request){
        throw new NotYetImplementedException();
    }
}
