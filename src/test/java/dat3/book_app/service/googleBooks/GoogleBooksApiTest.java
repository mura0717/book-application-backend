package dat3.book_app.service.googleBooks;

import dat3.book_app.dto.books.pagination.BookPaginatedResponse;
import dat3.book_app.dto.books.search.BookSearchResponse;
import dat3.book_app.entity.books.GoogleBook;
import dat3.book_app.factory.googleBooks.filters.GoogleBooksFilters;
import dat3.book_app.factory.googleBooks.filters.GoogleBooksV1Filters;
import dat3.book_app.factory.googleBooks.query.GoogleBooksV1QueryUrls;
import dat3.book_app.factory.googleBooks.query.GoogleBooksQueryUrls;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GoogleBooksApiTest {

    GoogleBooksQueryUrls googleBooksParamsFactory = new GoogleBooksV1QueryUrls();
    GoogleBooksFilters googleBooksFilters = new GoogleBooksV1Filters();
    GoogleBooksApi googleBooksApi = new GoogleBooksApi(googleBooksParamsFactory, googleBooksFilters);

    @Test
    void bySearch() {
        List<BookSearchResponse> responses = googleBooksApi.bySearch("Harry Potter");
        assertEquals(true, responses != null);
        assertEquals(true, responses.size() > 0);
        assertEquals(true, responses.stream().allMatch(res -> res.getTitle().contains("Harry Potter")));
    }

    @Test
    void byAuthor() {
        List<GoogleBook> responses1 = googleBooksApi.getBooksByAuthor("J.K. Rowling");
        assertEquals(true, responses1 != null);
        assertEquals(true, responses1.size() > 0);

        List<GoogleBook> responses2 = googleBooksApi.getBooksByAuthor("George R.R. Martin");
        assertEquals(true, responses2 != null);
        assertEquals(true, responses2.size() > 0);
    }

    @Test
    void slice() {
        List<BookPaginatedResponse> responses = googleBooksApi.slice();
        assertEquals(true, responses != null);
        assertEquals(true, responses.size() > 0);
    }
}