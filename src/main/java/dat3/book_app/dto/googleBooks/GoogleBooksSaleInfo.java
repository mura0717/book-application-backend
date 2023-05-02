package dat3.book_app.dto.googleBooks;

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
    private ListPrice listPrice;
    private RetailPrice retailPrice;
    private String buyLink;

}

@Getter
@Setter
@NoArgsConstructor
class ListPrice {
    private double amount;
    private String currencyCode;
}

@Getter
@Setter
@NoArgsConstructor
class RetailPrice {
    private double amount;
    private String currencyCode;
}

