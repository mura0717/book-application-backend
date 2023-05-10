package dat3.book_app.factory.googleBooks.query;

import dat3.book_app.utils.BookUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoogleBooksV1QueryUrls implements GoogleBooksQueryUrls {
    private final String Uri = "https://www.googleapis.com/books/v1/volumes";
    private BookUtils bookUtils = new BookUtils();


    @Override
    public String queryRandomBooks() {
        Random random = new Random();

        int randomBaseParamIndex = random.nextInt(bookUtils.BASE_PARAMS.size());
        String baseParams = bookUtils.BASE_PARAMS.get(randomBaseParamIndex);

        int randomBookTitleIndex = random.nextInt(bookUtils.BOOK_TITLE_KEYWORDS.size());
        String q = bookUtils.BOOK_TITLE_KEYWORDS.get(randomBookTitleIndex);

        return Uri + String.format(
                baseParams,
                q
        );
    }

    @Override
    public String queryRandomBooksWithGenre(String genre) {
        Random random = new Random();

        int randomBaseParamIndex = random.nextInt(bookUtils.BASE_PARAMS_WITH_GENRE.size());
        String baseParams = bookUtils.BASE_PARAMS_WITH_GENRE.get(randomBaseParamIndex);

        // retrieve book title word list for specific genre
        List<String> qList =  bookUtils.GENRE_WORDS_MAP.entrySet().stream()
                .filter(w -> w.getKey().equalsIgnoreCase(genre))
                .findFirst()
                .get()
                .getValue();

        // pick random word from the queried book title word list
        String q = qList.get(random.nextInt(qList.size()));

        // Due to encoding, a genre like "science+fiction" won't automatically work,
        // therefore the '+' is replaced with '_' prior.
        // This is what we are checking for and replacing, if any '_' were to exist.
        String genreToUse =genre.contains("_") ? genre.replaceAll("_", "+") : genre;
        return Uri + String.format(
                baseParams,
                q,
                genreToUse
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
