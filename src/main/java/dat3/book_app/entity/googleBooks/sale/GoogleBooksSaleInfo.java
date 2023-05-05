package dat3.book_app.entity.googleBooks.sale;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoogleBooksSaleInfo {
    private String country;
    private String saleability;
    private String isEbook;
    private ListPrice listPrice = new ListPrice();
    private RetailPrice retailPrice = new RetailPrice();
    private String buyLink;
}

