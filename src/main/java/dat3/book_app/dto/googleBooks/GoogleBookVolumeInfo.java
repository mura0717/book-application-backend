package dat3.book_app.dto.googleBooks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class GoogleBookVolumeInfo {
    private String title;
    private ArrayList<String> authors = new ArrayList<>();
    private String publisher;
    private String publishedDate;
    private String description;
    private ArrayList<String> categories = new ArrayList<>();
    private String maturityRating;
    private String language;
    private ImageLinks imageLinks;
}

@Getter
@Setter
@NoArgsConstructor
class ImageLinks {
    private String smallThumbnail;
    private String thumbnail;
    private String small;
    private String medium;
    private String large;
    private String extraLarge;
}
