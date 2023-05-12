package dat3.book_app.service.http;

import reactor.core.publisher.Mono;

public interface IHttpFetch {
    <T> T getRequest(String uri, Class<T> descriptor);
    <T> Mono<T> getRequestAsync(String uri, Class<T> descriptor);
}
