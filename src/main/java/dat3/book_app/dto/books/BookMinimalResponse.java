package dat3.book_app.dto.books;

import dat3.book_app.entity.googleBooks.GoogleBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class BookMinimalResponse {
    public BookMinimalResponse(GoogleBook book) {
        id = book.getId();
        title = book.getVolumeInfo().getTitle();
        authors = book.getVolumeInfo().getAuthors();
        categories = book.getVolumeInfo().getCategories();
        priceAmount = book.getSaleInfo().getRetailPrice().getAmount();
        currency = book.getSaleInfo().getRetailPrice().getCurrencyCode();
    }

    private String title;

    private ArrayList<String> authors;
    private ArrayList<String> categories;
    private double priceAmount;
    private String currency;
    private String id;
}
