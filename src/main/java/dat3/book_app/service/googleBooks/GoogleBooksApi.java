package dat3.book_app.service.googleBooks;

import dat3.book_app.dto.googleBooks.BookResponse;
import dat3.book_app.dto.googleBooks.GoogleBooksAPIResponse;
import dat3.book_app.entity.bookRecommendations.BookRecommendation;
import dat3.book_app.factory.GoogleBooksQueryUrls;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GoogleBooksApi implements IGoogleBooksApi {

    private final String Uri = "https://www.googleapis.com/books/v1/volumes";

    private final GoogleBooksQueryUrls _queryUrls;

    public GoogleBooksApi(GoogleBooksQueryUrls googleBooksParamsFactory) {
        _queryUrls = googleBooksParamsFactory;
    }

    @Override
    public BookResponse byReference(String bookReference){
        var query = String.format("%s/%s",Uri,bookReference);
        var response = getRequest(query,BookResponse.class);
        return response != null ? response : new BookResponse();
    }

    @Override
    public List<BookResponse> byAuthor(String author){
        var uri = _queryUrls.queryBookByAuthor(author);
        var response = getRequest(uri,GoogleBooksAPIResponse.class);
        return response != null ? response.getItems() : new ArrayList<>();
    }

    @Override
    public List<BookResponse> fromAiRecommendations(List<BookRecommendation> recommendations) {
        return  recommendations.stream()
                .map(r -> _queryUrls.queryBook(r.getAuthors().get(0), r.getTitle()))
                .map(u -> getRequestAsync(u, GoogleBooksAPIResponse.class))
                .filter(Objects::nonNull)
                .map(Mono::block)
                .filter(Objects::nonNull)
                .map(r -> r.getItems().get(0))
                .toList();
    }

    @Override
    public List<BookResponse> bySearch(String query) {
        var uri = String.format("%s?q='%s'&maxResults=5&filter=paid-ebooks&langRestrict",Uri,query);
        var response = getRequest(uri,GoogleBooksAPIResponse.class);
        return response != null ? response.getItems() : new ArrayList<>();
    }

    @Override
    public List<BookResponse> slice() {
        String fullURI = _queryUrls.queryRandomBooks();
        var response = getRequest(fullURI,GoogleBooksAPIResponse.class);
        return response != null ? response.getItems() : new ArrayList<>();
    }

    private <T> T getRequest(String uri, Class<T> descriptor){
        try {
            return WebClient.create()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(descriptor)
                    .block();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
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
