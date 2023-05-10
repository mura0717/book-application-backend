package dat3.book_app.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BookUtils {

    public final List<String> BASE_PARAMS = Arrays.asList(
            "?q=%s&maxResults=16&printType=books&filter=paid-ebooks",
            "?q=%s&maxResults=16&printType=books&filter=paid-ebooks&langRestrict=da"
    );

    public final List<String> BASE_PARAMS_WITH_GENRE = Arrays.asList(
            "?q=%s+subject:%s&maxResults=16&printType=books&filter=paid-ebooks",
            "?q=%s+subject:%s&maxResults=16&printType=books&filter=paid-ebooks&langRestrict=da"
    );

    public final HashMap<String, List<String>> GENRE_WORDS_MAP = new HashMap<>() {{
        put("science_fiction", Arrays.asList(
                "space",
                "time",
                "world",
                "star",
                "future",
                "alien",
                "galactic",
                "war",
                "robot",
                "empire",
                "mind",
                "cyber",
                "mutation",
                "DNA",
                "parallel",
                "dimension",
                "quantum",
                "artificial",
                "apocalyptic"
        ));
        put("fiction", Arrays.asList(
                "love",
                "life",
                "time",
                "story",
                "secret",
                "house",
                "death",
                "night",
                "day",
                "way",
                "world",
                "heart",
                "city",
                "family",
                "game",
                "island",
                "dream",
                "journey"
        ));
        put("thriller", Arrays.asList(
                "killer",
                "murder",
                "death",
                "fear",
                "blood",
                "betrayal",
                "suspicion",
                "shadow",
                "hunt",
                "chase",
                "dark",
                "danger",
                "secret",
                "revenge",
                "game",
                "lies",
                "witness",
                "traitor",
                "hostage"
        ));
        put("novel", Arrays.asList(
                "love",
                "life",
                "time",
                "story",
                "secret",
                "day",
                "way",
                "family",
                "city",
                "island",
                "dream",
                "journey",
                "song"
        ));
        put("poetry", Arrays.asList(
                "love",
                "songs",
                "poems",
                "light",
                "dark",
                "dreams",
                "voices",
                "rain",
                "wind",
                "time",
                "shadows",
                "fire",
                "river",
                "earth",
                "stars",
                "wings",
                "flowers",
                "silence",
                "heart",
                "journey"
        ));
    }};

    public List<String> BOOK_TITLE_KEYWORDS = Arrays.asList(
            "scary",
            "story",
            "new",
            "study",
            "cold",
            "shadow",
            "money",
            "rain",
            "door",
            "dreams",
            "storm",
            "secret",
            "game",
            "gone",
            "dark",
            "summer",
            "business",
            "magic",
            "fall",
            "light",
            "summer"
    );
}
