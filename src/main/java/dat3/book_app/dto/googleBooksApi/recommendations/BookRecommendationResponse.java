package dat3.book_app.dto.googleBooksApi.recommendations;

import dat3.book_app.entity.googleBooksApi.GoogleBook;
import dat3.book_app.entity.googleBooksApi.volumeInfo.GoogleBookImageInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookRecommendationResponse {
    public BookRecommendationResponse(GoogleBook book){
        reference = book.getId();
        title = book.getVolumeInfo().getTitle();
        authors = book.getVolumeInfo().getAuthors();
        imageLink = book.getVolumeInfo().getImageLinks();
        priceAmount = book.getSaleInfo().getRetailPrice().getAmount();
        currency = book.getSaleInfo().getRetailPrice().getCurrencyCode();
    }

    private String reference;
    private GoogleBookImageInfo imageLink;
    private String title;
    private List<String> authors;
    private double priceAmount;
    private String currency;
}
