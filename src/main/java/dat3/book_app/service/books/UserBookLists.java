package dat3.book_app.service.books;

import dat3.book_app.dto.books.BookListResponse;
import dat3.book_app.repository.BooklistRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserBookLists implements BookLists {
    private final AuthenticationManager _auth;
    private final BooklistRepository _repository;

    public UserBookLists(AuthenticationManager auth, BooklistRepository repository) {
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
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
            return authentication.getName();
        return null;
    }
}
