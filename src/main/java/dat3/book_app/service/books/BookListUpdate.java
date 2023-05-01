package dat3.book_app.service.books;

import dat3.book_app.dto.books.BookListUpdateRequest;
import dat3.book_app.repository.BooklistRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookListUpdate implements IBookListUpdate<BookListUpdateRequest> {

    private final BooklistRepository _repository;

    public BookListUpdate(BooklistRepository repository) {
        _repository = repository;
    }

    @Override
    public ResponseEntity<String> Update(BookListUpdateRequest request) {
        var bookList = _repository.findById(request.getBookListId())
                .orElse(null);
        if(bookList == null)
            return errorResponse("BookList not found");
        var bookReferences = bookList.getBookReferences();
        var isPresent = bookReferences.stream()
                .anyMatch(s -> s.equals(request.getBookId()));
        if(isPresent)
           return errorResponse("Book already present");
        bookReferences.add(request.getBookListId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<String> errorResponse(String message){
        return new ResponseEntity<>(message,HttpStatus.NOT_MODIFIED);
    }
}
