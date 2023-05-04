package dat3.book_app.factory;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class GoogleBooksV1QueryUrls implements GoogleBooksQueryUrls {
    private final String Uri = "https://www.googleapis.com/books/v1/volumes";

    private final List<String> BASE_PARAMS = Arrays.asList(
            "?q=%s&maxResults=15&printType=books&filter=paid-ebooks",
            "?q=%s&maxResults=15&printType=books&filter=paid-ebooks&langRestrict=da"
    );

    private List<String> KEYWORDS = Arrays.asList(
            "a",
            "scary",
            "the",
            "story",
            "new",
            "study",
            "report",
            "cold",
            "shadow",
            "noble",
            "money",
            "good",
            "rain",
            "door",
            "dreams",
            "city",
            "storm",
            "secret",
            "game",
            "gone",
            "dark",
            "summer",
            "after",
            "love",
            "end",
            "magic",
            "fall",
            "first",
            "light",
            "cross",
            "summer"
    );

    @Override
    public String queryRandomBooks() {
        Random random = new Random();
        String baseParams = BASE_PARAMS.get(random.nextInt(BASE_PARAMS.size()));
        String q = KEYWORDS.get(random.nextInt(KEYWORDS.size()));
        return Uri + String.format(
                baseParams,
                q
        );
    }

    @Override
    public String queryBook(String author, String title) {
        return Uri + String.format("?q=author:%s&title:%s",author,title);
    }

    @Override
    public String queryBookByAuthor(String author) {
        return Uri + String.format("?q=author:%s",author);
    }

    @Override
    public String queryBookByReference(String reference) {
        return String.format("%s/%s",Uri,reference);
    }
}
