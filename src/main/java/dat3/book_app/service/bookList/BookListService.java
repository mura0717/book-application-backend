package dat3.book_app.service.bookList;

import dat3.book_app.dto.bookList.BookReferenceResponse;
import dat3.book_app.entity.books.GoogleBook;
import dat3.book_app.repository.BookRepository;
import dat3.book_app.repository.BooklistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookListService {

    private final BooklistRepository _bookListRepository;
    private final BookRepository _bookRepository;


    public BookListService(BooklistRepository bookListRepository, BookRepository bookRepository) {
        _bookListRepository = bookListRepository;
        _bookRepository = bookRepository;
    }
/*
    public List<BookReferenceResponse> getBooksInBookList(){
        List<GoogleBook> books = _bookRepository.findAll();
        return books.stream().map(book -> new BookReferenceResponse(book)).collect(Collectors.toList());


        return null;
    }*/
}
