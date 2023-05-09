package dat3.book_app.entity.books.sale;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
class ListPrice {
    private double amount;
    private String currencyCode;
}
