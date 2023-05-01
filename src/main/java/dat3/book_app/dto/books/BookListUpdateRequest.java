package dat3.book_app.dto.books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookListUpdateRequest {
    private String bookId;
    private String BookListId;
}
