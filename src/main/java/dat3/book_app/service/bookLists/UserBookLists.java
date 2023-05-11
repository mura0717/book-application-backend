package dat3.book_app.service.bookLists;

import dat3.book_app.dto.bookLists.request.BookListCreateRequest;
import dat3.book_app.dto.bookLists.request.BookListUpdateRequest;
import dat3.book_app.dto.bookLists.response.BookListUpdateResponse;
import dat3.book_app.dto.bookLists.response.BookListWithBooks;
import dat3.book_app.dto.bookLists.response.BookListWithReferences;
import dat3.book_app.dto.bookLists.response.BookListsTitleResponse;
import dat3.book_app.repository.BooklistRepository;
import dat3.book_app.service.googleBooks.IGoogleBooksApi;
import dat3.security.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_MODIFIED;

@Service
public class UserBookLists implements BookLists {
    private final BooklistRepository _bookLists;
    private final IGoogleBooksApi googleBooks;
    private final MemberRepository _members;

    public UserBookLists(BooklistRepository repository, IGoogleBooksApi googleBooks, MemberRepository members) {
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
        var isPresent = bookReferences.contains(request.getBookReference());
        if(isPresent)
            return new BookListUpdateResponse("Book already added",false);
        bookReferences.add(request.getBookReference());
        _bookLists.save(bookList);
        return new BookListUpdateResponse("Ok",true);
    }

    @Override
    public BookListsTitleResponse createBookList(BookListCreateRequest request, String username) {
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

    @Override
    public boolean bookAlreadyAdded(String bookListId, String bookReference) {
        var bookList = _bookLists.findById(bookListId).orElse(null);
        if(bookList == null)
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND,"Booklist not found");
        return bookList.getBookReferences().contains(bookReference);
    }
}
