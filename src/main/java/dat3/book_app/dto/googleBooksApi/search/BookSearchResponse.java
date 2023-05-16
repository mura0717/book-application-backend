package dat3.book_app.dto.googleBooksApi.search;

import dat3.book_app.entity.googleBooksApi.GoogleBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookSearchResponse {

    public BookSearchResponse(GoogleBook book) {
        this.id = book.getId();
        this.smallThumbnail = book.getVolumeInfo().getImageLinks().getSmallThumbnail();
        this.title = book.getVolumeInfo().getTitle();
        this.authors = book.getVolumeInfo().getAuthors();
        this.retailPrice = book.getSaleInfo().getRetailPrice().getAmount();
        this.currencyCode = book.getSaleInfo().getRetailPrice().getCurrencyCode();
    }

    private String id;
    private String smallThumbnail;
    private String title;
    private List<String> authors;
    private double retailPrice;
    private String currencyCode;
}
