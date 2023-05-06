package dat3.book_app.service.bookLists;

import dat3.book_app.dto.books.BookListMinimumResponse;
import dat3.book_app.dto.books.BookListUpdateRequest;
import dat3.book_app.entity.Booklist;
import dat3.book_app.repository.BooklistRepository;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookLists implements BookLists {
    private final BooklistRepository _repository;

    public UserBookLists(BooklistRepository repository) {
        _repository = repository;
    }

    @Override
    public List<BookListMinimumResponse> bookLists(String username) {
        var userLists = _repository.findByUser_UsernameLike(username);
        return userLists.stream().map(BookListMinimumResponse::new).toList();
    }

    @Override
    public Booklist bookList(String id) {
        return _repository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<String> Update(BookListUpdateRequest request) {
        var bookList = _repository.findById(request.getBookListId())
                .orElse(null);
        if(bookList == null)
            return errorResponse("BookList not found");
        var bookReferences = bookList.getBookReferences();
        var isPresent = bookReferences.contains(request.getBookId());
        if(isPresent)
            return errorResponse("Book already present");
        bookReferences.add(request.getBookId());
        _repository.save(bookList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<String> errorResponse(String message){
        var jsonObject = new JSONObject();
        jsonObject.put("message",message);
        var json = jsonObject.toString();
        return new ResponseEntity<>(json,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
