package dat3.book_app.entity.googleBooks.volumeInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public
class GoogleBookImageInfo {
    private String smallThumbnail;
    private String thumbnail;
    private String small;
    private String medium;
    private String large;
    private String extraLarge;
}
