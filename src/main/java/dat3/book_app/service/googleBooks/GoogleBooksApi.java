package dat3.book_app.service.googleBooks;

import dat3.book_app.dto.googleBooks.BookResponse;
import dat3.book_app.dto.googleBooks.GoogleBooksAPIResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleBooksApi implements IGoogleBooksApi {
    private final String Uri = "https://www.googleapis.com/books/v1/volumes";

    @Override
    public BookResponse byReference(String bookReference){
        var uri = String.format("%s/%s",Uri,bookReference);
        var response = getRequest(uri,BookResponse.class);
        return response != null ? response : new BookResponse();
    }

    @Override
    public List<BookResponse> byAuthor(String author){
        var uri = String.format("%s?q='inauthor:'%s'",Uri,author);
        var response = getRequest(uri,GoogleBooksAPIResponse.class);
        return response != null ? response.getItems() : new ArrayList<>();
    }

    @Override
    public List<BookResponse> byKeyword(String keyword){
        var uri = String.format("%s?q='%s'",Uri,keyword);
        var response = getRequest(uri,GoogleBooksAPIResponse.class);
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
            return null;
        }
    }
}
