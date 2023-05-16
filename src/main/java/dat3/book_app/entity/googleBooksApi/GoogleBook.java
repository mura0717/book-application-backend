package dat3.book_app.entity.googleBooksApi;

import dat3.book_app.entity.googleBooksApi.sale.GoogleBooksSaleInfo;
import dat3.book_app.entity.googleBooksApi.volumeInfo.GoogleBookVolumeInfo;
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
