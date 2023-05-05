package dat3.book_app.entity.googleBooks.sale;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RetailPrice {
    private double amount;
    private String currencyCode;
}
