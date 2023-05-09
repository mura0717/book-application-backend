package dat3.book_app.service.googleBooks;

import dat3.book_app.entity.books.GoogleBook;
import dat3.book_app.factory.googleBooks.GoogleBooksV1QueryUrls;
import dat3.book_app.factory.googleBooks.GoogleBooksQueryUrls;
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
        List<GoogleBook> responses = googleBooksApi.bySearch("Harry Potter");
        assertEquals(true, responses != null);
        assertEquals(true, responses.size() > 0);
        assertEquals(true, responses.stream().allMatch(res -> res.getVolumeInfo().getTitle().contains("Harry Potter")));
    }

    @Test
    void slice() {
        List<GoogleBook> responses = googleBooksApi.slice();
        assertEquals(true, responses != null);
        assertEquals(true, responses.size() > 0);
    }
}