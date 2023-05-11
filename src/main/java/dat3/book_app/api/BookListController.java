package dat3.book_app.api;

import dat3.book_app.dto.bookLists.request.BookListCreateRequest;
import dat3.book_app.dto.bookLists.request.BookListUpdateRequest;
import dat3.book_app.dto.bookLists.response.BookListFullResponse;
import dat3.book_app.dto.bookLists.response.BookListMinimumResponse;
import dat3.book_app.dto.bookLists.response.BookListUpdateResponse;
import dat3.book_app.dto.bookLists.response.BookListsTitleResponse;
import dat3.book_app.service.bookLists.BookLists;
import dat3.book_app.service.googleBooks.IGoogleBooksApi;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bookLists")
@CrossOrigin
public class BookListController {
    private final BookLists bookLists;
    private final IGoogleBooksApi googleBooks;

    public BookListController(BookLists bookLists, IGoogleBooksApi googleBooks) {
        this.bookLists = bookLists;
        this.googleBooks = googleBooks;
    }

    @PatchMapping("/addToBookList")
    public BookListUpdateResponse addToBooklist(@RequestBody BookListUpdateRequest request){
        return bookLists.addToBookList(request);
    }

    @GetMapping
    public List<BookListMinimumResponse> bookLists(Principal principal){
        if(principal == null)
            return new ArrayList<>();
        return bookLists.bookLists(principal.getName());
    }

    @GetMapping("/")
    public BookListFullResponse bookList(String id){
        var bookList = bookLists.bookList(id);
        var books = googleBooks.fromReferences(bookList.getBookReferences());
        return new BookListFullResponse(bookList,books);
    }

    @PostMapping("/create")
    public BookListsTitleResponse create(@RequestBody BookListCreateRequest request, Principal principal){
        return bookLists.create(request,principal.getName());
    }

    @GetMapping("/titles")
    public List<BookListsTitleResponse> getTitles(Principal principal){
        return bookLists.listTitles(principal.getName());
    }
}
