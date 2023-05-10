package dat3.book_app.service.bookReferences;

import dat3.book_app.dto.bookReferences.BookReferenceResponse;
import dat3.book_app.dto.books.BookMinimalResponse;
import dat3.book_app.entity.bookLists.Booklist;
import dat3.book_app.entity.books.GoogleBook;
import dat3.book_app.factory.googleBooks.GoogleBooksQueryUrls;
import dat3.book_app.repository.BooklistRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookReferencesService {

    private final String Uri = "https://www.googleapis.com/books/v1/volumes";
    private final BooklistRepository _bookLists;
    private final GoogleBooksQueryUrls _queryUrls;

    public BookReferencesService(BooklistRepository _bookLists, GoogleBooksQueryUrls _queryUrls) {
        this._bookLists = _bookLists;
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

    public BookReferenceResponse addBookReference(Booklist booklist, String referenceId) {
        // Check if the reference already exists in the book list
        if (booklist.getBookReferences().contains(referenceId)) {
            throw new IllegalArgumentException("The reference already exists in the book list");
        }

        // Get the book information from the Google Books API
        var query = String.format("%s/%s", Uri, referenceId);
        var book = getRequestAsync(query, GoogleBook.class).block();

        // Create a new book reference and add it to the book list
        var bookReference = "https://www.googleapis.com/books/v1/volumes/12345";
        booklist.getBookReferences().add(bookReference);

        // Return the book reference response
        return new BookReferenceResponse(book);
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
