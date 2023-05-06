package dat3.book_app.entity.googleBooks;

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
    private GoogleBookImageInfo imageLinks = new GoogleBookImageInfo();
}

