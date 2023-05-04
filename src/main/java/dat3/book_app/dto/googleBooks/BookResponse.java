package dat3.book_app.dto.googleBooks;

import dat3.book_app.dto.googleBooks.googleSales.GoogleBooksSaleInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookResponse {
    private String id;
    private String etag;
    private GoogleBookVolumeInfo volumeInfo = new GoogleBookVolumeInfo();
    private GoogleBooksSaleInfo saleInfo = new GoogleBooksSaleInfo();
}
