package dat3.book_app.factory.googleBooks.filters;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class GoogleBooksV1Filters implements GoogleBooksFilters {


    @Override
    public HashMap<String, String> genres() {
        HashMap<String, String> genres = new HashMap<>() {{
            put("Fiction", "fiction");
            put("Science Fiction", "science_fiction");
            put("Poetry", "poetry");
            put("Novel", "novel");
            put("Thriller", "thriller");
        }};
        return genres;
    }
}
