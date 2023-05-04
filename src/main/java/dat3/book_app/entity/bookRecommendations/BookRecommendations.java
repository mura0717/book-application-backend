package dat3.book_app.entity.bookRecommendations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookRecommendations {
    private List<BookRecommendation> recommendations;
}
