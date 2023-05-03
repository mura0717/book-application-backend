package dat3.book_app.service.books;

import dat3.book_app.dto.books.BookListResponse;
import dat3.book_app.repository.BooklistRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookLists implements BookLists {
    private final BooklistRepository _repository;

    public UserBookLists(BooklistRepository repository) {
        _repository = repository;
    }

    @Override
    public List<BookListResponse> bookLists(String username) {
        var userLists = _repository.findByUser_UsernameLike(username);
        return userLists.stream().map(BookListResponse::new).toList();
    }
}
