package dat3.book_app.service.googleBooks;

import dat3.book_app.dto.bookLists.response.BookListBook;
import dat3.book_app.dto.books.BookDetailsResponse;

import dat3.book_app.dto.books.pagination.BookPaginatedResponse;
import dat3.book_app.dto.books.recommendations.BookRecommendationResponse;
import dat3.book_app.dto.books.search.BookSearchResponse;
import dat3.book_app.entity.bookRecommendations.BookRecommendation;
import dat3.book_app.entity.books.GoogleBook;
import dat3.book_app.entity.books.GoogleBooksAPIResponse;
import dat3.book_app.factory.googleBooks.filters.GoogleBooksFilters;
import dat3.book_app.factory.googleBooks.query.GoogleBooksQueryUrls;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class GoogleBooksApi implements IGoogleBooksApi {

    private final String Uri = "https://www.googleapis.com/books/v1/volumes";

    private final GoogleBooksQueryUrls _queryUrls;
    private final GoogleBooksFilters _googleBooksFilters;

    public GoogleBooksApi(GoogleBooksQueryUrls googleBooksParamsFactory, GoogleBooksFilters googleBooksFilters) {
        _queryUrls = googleBooksParamsFactory;
        _googleBooksFilters = googleBooksFilters;
    }

    @Override
    public BookDetailsResponse getBookByReference(String bookReference){
        var query = String.format("%s/%s",Uri,bookReference);
        var response = getRequest(query, GoogleBook.class);
        return response != null ? new BookDetailsResponse(response) : new BookDetailsResponse();
    }

    @Override
    public List<BookListBook> getBooksByReferences(List<String> references) {
        return references.stream().map(r -> {
                var query = String.format("%s/%s",Uri,r);
                return  getRequestAsync(query, GoogleBook.class);
            })
            .map(m -> m.block())
            .filter(Objects::nonNull)
            .map(BookListBook::new).toList();
    }

    @Override
    public List<GoogleBook> getBooksByAuthor(String author){
        var uri = _queryUrls.queryBookByAuthor(author);
        var response = getRequest(uri,GoogleBooksAPIResponse.class);
        return response != null ? response.getItems() : new ArrayList<>();
    }

    @Override
    public List<BookRecommendationResponse> fromAiRecommendations(List<BookRecommendation> recommendations) {
        return  recommendations.stream()
                .map(r -> _queryUrls.queryBook(r.getAuthors().get(0), r.getTitle()))
                .map(u -> getRequestAsync(u, GoogleBooksAPIResponse.class))
                .filter(Objects::nonNull)
                .map(Mono::block)
                .filter(Objects::nonNull)
                .map(r -> !r.getItems().isEmpty() ? r.getItems().get(0) : null )
                .filter(Objects::nonNull)
                .map(BookRecommendationResponse::new)
                .toList();
    }

    @Override
    public List<BookSearchResponse> bySearch(String query) {
        var uri = String.format("%s?q='%s'&maxResults=5&filter=paid-ebooks&langRestrict",Uri,query);
        var response = getRequest(uri,GoogleBooksAPIResponse.class);
        return response.getItems().stream().map(b -> new BookSearchResponse(b)).toList();
    }

    @Override
    public List<BookPaginatedResponse> slice() {
        String fullURI = _queryUrls.queryRandomBooks();
        var response = getRequest(fullURI,GoogleBooksAPIResponse.class);
        return response.getItems().stream().map(b -> new BookPaginatedResponse(b)).toList();
    }

    @Override
    public List<BookPaginatedResponse> sliceWithGenre(String genre) {
        String fullURI = _queryUrls.queryRandomBooksWithGenre(genre);
        var response = getRequest(fullURI,GoogleBooksAPIResponse.class);
        return response.getItems().stream().map(b -> new BookPaginatedResponse(b)).toList();
    }

    @Override
    public HashMap<String, String> availableGenres() {
        return _googleBooksFilters.genres();
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
