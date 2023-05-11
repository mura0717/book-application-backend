package dat3.book_app.service.bookLists;

import dat3.book_app.dto.bookLists.request.BookListCreateRequest;
import dat3.book_app.dto.bookLists.request.BookListUpdateRequest;
import dat3.book_app.dto.bookLists.response.BookListMinimumResponse;
import dat3.book_app.dto.bookLists.response.BookListUpdateResponse;
import dat3.book_app.dto.bookLists.response.BookListsTitleResponse;
import dat3.book_app.entity.bookLists.Booklist;
import dat3.book_app.repository.BooklistRepository;
import dat3.security.repository.MemberRepository;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import java.util.List;
import static org.springframework.http.HttpStatus.NOT_MODIFIED;

@Service
public class UserBookLists implements BookLists {
    private final BooklistRepository _bookLists;
    private final MemberRepository _members;

    public UserBookLists(BooklistRepository repository, MemberRepository members) {
        _bookLists = repository;
        _members = members;
    }

    @Override
    public List<BookListMinimumResponse> bookLists(String username) {
        var userLists = _bookLists.findByMember_UsernameLike(username);
        return userLists.stream().map(BookListMinimumResponse::new).toList();
    }

    @Override
    public List<BookListsTitleResponse> listTitles(String username) {
        var userLists = _bookLists.findByMember_UsernameLike(username);
        return userLists.stream().map(BookListsTitleResponse::new).toList();
    }

    @Override
    public Booklist bookList(String id) {
        return _bookLists.findById(id).orElse(null);
    }

    @Override
    public BookListUpdateResponse addToBookList(BookListUpdateRequest request) {
        var bookList = _bookLists.findById(request.getBookListId())
                .orElse(null);
        if(bookList == null)
            return new BookListUpdateResponse("BookList not found",false);
        var bookReferences = bookList.getBookReferences();
        var isPresent = bookReferences.contains(request.getBookReference());
        if(isPresent)
            return new BookListUpdateResponse("Book already added",false);
        bookReferences.add(request.getBookReference());
        _bookLists.save(bookList);
        return new BookListUpdateResponse("Ok",true);
    }

    @Override
    public BookListsTitleResponse create(BookListCreateRequest request, String username) {
        var exists = _bookLists.existsByTitleLike(request.getTitle());
        if(exists)
            throw new HttpServerErrorException(NOT_MODIFIED,"Already exists");
        var member = _members.findByUsernameLike(username).orElse(null);
        if(member == null)
            throw new HttpServerErrorException(NOT_MODIFIED,"Member not found");
        var bookList = request.toBookList(member);
        var saved = _bookLists.saveAndFlush(bookList);
        return new BookListsTitleResponse(saved);
    }

    private ResponseEntity<String> errorResponse(String message){
        var jsonObject = new JSONObject();
        jsonObject.put("message",message);
        var json = jsonObject.toString();
        return new ResponseEntity<>(json,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
