package dat3.book_app.api;

import dat3.book_app.dto.bookLists.request.BookListCreateRequest;
import dat3.book_app.dto.bookLists.request.BookListUpdateRequest;
import dat3.book_app.dto.bookLists.response.*;
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

    @PatchMapping("/removeFromBookList")
    public boolean removeFromBookList(@RequestBody BookListUpdateRequest request){
        return bookLists.removeFromBookList(request);
    }

    @GetMapping
    public List<BookListWithReferences> bookLists(Principal principal){
        if(principal == null)
            return new ArrayList<>();
        return bookLists.getBookListWithReferences(principal.getName());
    }

    @GetMapping("/")
    public BookListWithBooks bookList(String id){
        return bookLists.getBookListWithBooks(id);
    }

    @PostMapping("/create")
    public BookListCreateResponse create(@RequestBody BookListCreateRequest request, Principal principal){
        return bookLists.createBookList(request,principal.getName());
    }

    @GetMapping("/titles")
    public List<BookListsTitleResponse> getTitles(Principal principal){
        return bookLists.getBookListWithTitles(principal.getName());
    }

    @GetMapping("/alreadyExists")
    public boolean checkIfAlreadyAdded(String bookListId, String bookReference){
        return bookLists.bookAlreadyAdded(bookListId,bookReference);
    }
}
