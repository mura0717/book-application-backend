package dat3.book_app.entity.bookRecommendations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookRecommendation {
    private String title;
    private List<String> authors;
}
