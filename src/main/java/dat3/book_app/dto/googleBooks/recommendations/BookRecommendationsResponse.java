package dat3.book_app.dto.googleBooks.recommendations;

import dat3.book_app.entity.googleBooks.GoogleBook;
import dat3.book_app.entity.googleBooks.GoogleBookImageInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class BookRecommendationsResponse{
    public BookRecommendationsResponse(List<GoogleBook> books){
        recommendations = books.stream()
                .map(BookRecommendationResponse::new)
                .toList();
    }

    private List<BookRecommendationResponse> recommendations;
}

@Getter
class BookRecommendationResponse {
    public BookRecommendationResponse(GoogleBook book){
        reference = book.getId();
        title = book.getVolumeInfo().getTitle();
        authors = book.getVolumeInfo().getAuthors();
        imageLink = book.getVolumeInfo().getImageLinks();
        priceAmount = book.getSaleInfo().getRetailPrice().getAmount();
        currency = book.getSaleInfo().getRetailPrice().getCurrencyCode();
    }

    private final String reference;
    private final GoogleBookImageInfo imageLink;
    private final String title;
    private final List<String> authors;
    private final double priceAmount;
    private final String currency;
}
