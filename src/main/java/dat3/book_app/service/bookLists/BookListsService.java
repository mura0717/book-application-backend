package dat3.book_app.service.bookLists;

import dat3.book_app.dto.bookLists.request.BookListCreateRequest;
import dat3.book_app.dto.bookLists.request.BookListEditRequest;
import dat3.book_app.dto.bookLists.request.BookListUpdateRequest;
import dat3.book_app.dto.bookLists.response.BookListWithReferences;
import dat3.book_app.dto.bookLists.response.BookListsTitleResponse;
import dat3.book_app.entity.bookLists.Booklist;
import dat3.book_app.dto.bookLists.response.*;
import dat3.book_app.repository.BooklistRepository;
import dat3.book_app.service.googleBooks.IGoogleBooksApi;
import dat3.security.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import java.util.List;


@Service
public class BookListsService implements BookLists {
    private final BooklistRepository _bookLists;
    private final IGoogleBooksApi googleBooks;
    private final MemberRepository _members;

    public BookListsService(BooklistRepository repository, IGoogleBooksApi googleBooks, MemberRepository members) {
        _bookLists = repository;
        this.googleBooks = googleBooks;
        _members = members;
    }

    @Override
    public List<BookListWithReferences> getBookListWithReferences(String username) {
        var userLists = _bookLists.findByMember_UsernameLike(username);
        return userLists.stream().map(BookListWithReferences::new).toList();
    }

    @Override
    public List<BookListsTitleResponse> getBookListWithTitles(String username) {
        var userLists = _bookLists.findByMember_UsernameLike(username);
        return userLists.stream().map(BookListsTitleResponse::new).toList();
    }

    @Override
    public BookListWithBooks getBookListWithBooks(String id) {
        var bookList = _bookLists.findById(id).orElse(null);
        if(bookList == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        var books = googleBooks.getBooksByReferences(bookList.getBookReferences());
        return new BookListWithBooks(bookList,books);
    }

    @Override
    public BookListUpdateResponse addToBookList(BookListUpdateRequest request) {
        var bookList = _bookLists.findById(request.getBookListId())
                .orElse(null);
        if(bookList == null)
            return new BookListUpdateResponse("BookList not found",false);
        var bookReferences = bookList.getBookReferences();
        if(bookReferences.contains(request.getBookId()))
            return new BookListUpdateResponse("Book already added",false);
        bookReferences.add(request.getBookId());
        _bookLists.save(bookList);
        return new BookListUpdateResponse("Ok",true);
    }

    @Override
    public boolean removeFromBookList(BookListUpdateRequest request) {
        var bookList = _bookLists.findById(request.getBookListId())
                .orElse(null);
        if(bookList == null)
            return false;
        bookList.getBookReferences().remove(request.getBookId());
        _bookLists.save(bookList);
        return true;
    }

    @Override
    public BookListCreateResponse createBookList(BookListCreateRequest request, String username) {
        var exists = _bookLists.existsByTitleLike(request.getTitle());
        if(exists)
            return new BookListCreateResponse("Booklist already exist",false);
        var member = _members.findByUsernameLike(username).orElse(null);
        if(member == null)
            return new BookListCreateResponse("Member doesn't exist",false);
        var bookList = request.toBookList(member);
        var saved = _bookLists.saveAndFlush(bookList);
        var count = _bookLists.count();
        return new BookListCreateResponse("OK", count,true,saved);
    }

    @Override
    public boolean bookAlreadyAdded(String bookListId, String bookReference) {
        var bookList = _bookLists.findById(bookListId).orElse(null);
        if(bookList == null)
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND,"Booklist not found");
        return bookList.getBookReferences().contains(bookReference);
    }

    @Override
    public BookListUpdateResponse deleteBookList(String bookListId){
        System.out.println(bookListId);
        var bookList = _bookLists.findById(bookListId);
        if(bookList.isEmpty())
            return new BookListUpdateResponse("BookList not found",false);
        try{
            _bookLists.delete(bookList.get());
            return new BookListUpdateResponse("Ok",true);
        } catch (Exception e){
            return new BookListUpdateResponse("Error",false);
        }
    }

    @Override
    public BookListUpdateResponse editBookList(BookListEditRequest request){
        String bookListId = request.getBookListId();
        Booklist bookListToEdit = _bookLists.findById(bookListId).orElse(null);
        if(bookListToEdit == null)
            return new BookListUpdateResponse("Booklist not found",false);
        bookListToEdit.setTitle(request.getTitle());
        _bookLists.save(bookListToEdit);
        return new BookListUpdateResponse("OK",true);
    }

/*    @Override
    public BookListUpdateResponse removeFromBookList (BookListUpdateRequest request){
        Booklist bookList = _bookLists.findById(request.getBookListId()).orElseThrow(() ->
        new HttpServerErrorException(HttpStatus.NOT_FOUND,"Booklist not found"));
        bookList.getBookReferences().remove(request.getBookId());
        Booklist updatedBookList = _bookLists.save(bookList);
        return new BookListUpdateResponse(updatedBookList);
    }*/
}
