package dat3.book_app.dto.bookLists.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookListEditRequest {
    private String bookListId;
    private String title;
}
