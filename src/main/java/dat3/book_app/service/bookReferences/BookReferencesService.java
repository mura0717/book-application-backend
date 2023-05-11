package dat3.book_app.service.bookReferences;

import dat3.book_app.dto.bookLists.BookListUpdateRequest;
import dat3.book_app.dto.bookLists.BookReferenceResponse;
import dat3.book_app.entity.books.GoogleBook;
import dat3.book_app.factory.googleBooks.query.GoogleBooksQueryUrls;
import dat3.book_app.repository.BooklistRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
public class BookReferencesService {

    private final String Uri = "https://www.googleapis.com/books/v1/volumes";
    private final BooklistRepository booklistRepository;
    private final GoogleBooksQueryUrls _queryUrls;

    public BookReferencesService(BooklistRepository _bookLists, GoogleBooksQueryUrls _queryUrls) {
        this.booklistRepository = _bookLists;
        this._queryUrls = _queryUrls;
    }

    public List<BookReferenceResponse> getBookReferences(List<String> references) {

        return references.stream().map(r -> {
                    var query = String.format("%s/%s",Uri,r);
                    return  getRequestAsync(query, GoogleBook.class);
                })
                .map(m -> m.block())
                .filter(Objects::nonNull)
                .map(BookReferenceResponse::new).toList();
    }

    public ResponseEntity<String> addBookReference(BookListUpdateRequest request) {
        var booklist = booklistRepository.findById(request.getBookListId())
                .orElse(null);
        if (booklist == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Booklist not found");
        }
        var referenceId = request.getBookReference();
        if (booklist.getBookReferences().contains(referenceId)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Book already exists in list");
        }
        booklist.getBookReferences().add(referenceId);
        booklistRepository.save(booklist);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> removeBookReference(BookListUpdateRequest request) {
        var booklist = booklistRepository.findById(request.getBookListId())
                .orElse(null);
        if (booklist == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Booklist not found");
        }
        var referenceId = request.getBookReference();
        if (!booklist.getBookReferences().contains(referenceId)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Book does not exist in list");
        }
        booklist.getBookReferences().remove(referenceId);
        booklistRepository.save(booklist);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    private <T> Mono<T> getRequestAsync(String uri, Class<T> descriptor){
        try {
            return WebClient.create()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(descriptor);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }



}
