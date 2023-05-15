package dat3.book_app.service.http;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HttpFetch implements IHttpFetch {
    @Override
    public <T> T getRequest(String uri, Class<T> descriptor){
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

    @Override
    public <T> Mono<T> getRequestAsync(String uri, Class<T> descriptor){
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
