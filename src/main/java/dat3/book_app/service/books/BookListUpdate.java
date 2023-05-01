package dat3.book_app.service.books;

import dat3.book_app.dto.books.BookListUpdateRequest;
import dat3.book_app.repository.BooklistRepository;
import org.springframework.stereotype.Service;

@Service
public class BookListUpdate implements IBookListUpdate<BookListUpdateRequest> {

    private final BooklistRepository _repository;

    public BookListUpdate(BooklistRepository repository) {
        _repository = repository;
    }

    @Override
    public boolean Update(BookListUpdateRequest bookListUpdateRequest) {
        return false;
    }
}
