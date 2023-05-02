package dat3.book_app.service.books;

import dat3.book_app.dto.books.BookListResponse;
import dat3.book_app.repository.BooklistRepository;
import dat3.security.entity.UserWithRoles;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBookLists implements BookLists {
    private final Authentication _auth;
    private final BooklistRepository _repository;

    public UserBookLists(Authentication auth, BooklistRepository repository) {
        _auth = auth;
        _repository = repository;
    }

    @Override
    public List<BookListResponse> bookLists() {
        var username = getUsername();
        if(username == null)
            return new ArrayList<>();
        var userLists = _repository.findByUser_UsernameLike(username);
        return userLists.stream().map(BookListResponse::new).toList();
    }

    private String getUsername(){
        var user = (UserWithRoles) _auth.getPrincipal();
        return user.getUsername();
    }
}
