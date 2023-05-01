package dat3.book_app.service.books;

import org.springframework.http.ResponseEntity;

public interface IBookListUpdate<TRequest> {
    ResponseEntity<String> Update(TRequest request);
}
