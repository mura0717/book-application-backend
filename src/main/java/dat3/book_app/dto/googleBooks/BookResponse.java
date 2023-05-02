package dat3.book_app.dto.googleBooks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookResponse {
    private String id;
    private String etag;
    private GoogleBookVolumeInfo volumeInfo;
    private GoogleBooksSaleInfo saleInfo;
}
