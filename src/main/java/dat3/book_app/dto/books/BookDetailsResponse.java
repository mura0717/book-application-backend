package dat3.book_app.dto.books;

import dat3.book_app.entity.books.GoogleBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class BookDetailsResponse {
    public BookDetailsResponse(GoogleBook book) {
        reference = book.getId();
        title = book.getVolumeInfo().getTitle();
        authors = book.getVolumeInfo().getAuthors();
        categories = book.getVolumeInfo().getCategories();
        priceAmount = book.getSaleInfo().getRetailPrice().getAmount();
        currency = book.getSaleInfo().getRetailPrice().getCurrencyCode();
        image = book.getVolumeInfo().getImageLinks().getThumbnail();
        buyLink = book.getSaleInfo().getBuyLink();
        description = book.getVolumeInfo().getDescription();
        publisher = book.getVolumeInfo().getPublisher();
    }

    private String title = "";

    private ArrayList<String> authors = new ArrayList<>();
    private ArrayList<String> categories = new ArrayList<>() ;
    private double priceAmount = 0.0;
    private String currency = "";
    private String reference = "";
    private String image = "";
    private String buyLink = "";
    private String description = "";
    private String publisher = "";
}
