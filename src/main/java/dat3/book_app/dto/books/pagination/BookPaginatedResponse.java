package dat3.book_app.dto.books.pagination;

import dat3.book_app.entity.books.GoogleBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookPaginatedResponse {
    public BookPaginatedResponse(GoogleBook book) {
        this.id = book.getId();
        this.thumbnail = book.getVolumeInfo().getImageLinks().getThumbnail();
        this.title = book.getVolumeInfo().getTitle();
        this.description = book.getVolumeInfo().getDescription();
        this.authors = book.getVolumeInfo().getAuthors();
        this.retailPrice = book.getSaleInfo().getRetailPrice().getAmount();
        this.currencyCode = book.getSaleInfo().getRetailPrice().getCurrencyCode();
    }

    private String id;
    private String thumbnail;
    private String title;
    private String description;
    private List<String> authors;
    private double retailPrice;
    private String currencyCode;
}
