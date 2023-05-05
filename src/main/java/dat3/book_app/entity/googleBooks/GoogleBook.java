package dat3.book_app.entity.googleBooks;

import dat3.book_app.entity.googleBooks.sale.GoogleBooksSaleInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoogleBook {
    private String id;
    private String etag;
    private GoogleBookVolumeInfo volumeInfo = new GoogleBookVolumeInfo();
    private GoogleBooksSaleInfo saleInfo = new GoogleBooksSaleInfo();
}
