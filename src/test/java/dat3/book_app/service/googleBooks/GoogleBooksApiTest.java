package dat3.book_app.service.googleBooks;

import dat3.book_app.dto.googleBooks.BookResponse;
import dat3.book_app.factory.GoogleBooksV1QueryUrls;
import dat3.book_app.factory.GoogleBooksQueryUrls;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GoogleBooksApiTest {

    GoogleBooksQueryUrls googleBooksParamsFactory = new GoogleBooksV1QueryUrls();
    GoogleBooksApi googleBooksApi = new GoogleBooksApi(googleBooksParamsFactory);

    @Test
    void bySearch() {
        List<BookResponse> responses = googleBooksApi.bySearch("Harry Potter");
        assertEquals(true, responses != null);
        assertEquals(true, responses.size() > 0);
        assertEquals(true, responses.stream().allMatch(res -> res.getVolumeInfo().getTitle().contains("Harry Potter")));
    }

    @Test
    void slice() {
        List<BookResponse> responses = googleBooksApi.slice();
        assertEquals(true, responses != null);
        assertEquals(true, responses.size() > 0);
    }
}