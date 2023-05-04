package dat3.book_app.factory;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class GoogleBooksURLFactory {

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


    public String buildURI() {
        Random random = new Random();
        String baseParams = BASE_PARAMS.get(random.nextInt(BASE_PARAMS.size()));
        String q = KEYWORDS.get(random.nextInt(KEYWORDS.size()));
        String uri = String.format(
                baseParams,
                q
        );
        return uri;
    }

}
