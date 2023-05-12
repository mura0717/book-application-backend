package dat3.book_app.dto.bookLists.response;

import dat3.book_app.entity.bookLists.Booklist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookListWithReferences {
    public BookListWithReferences(Booklist list) {
        title = list.getTitle();
        listCount = list.getBookReferences().size();
        id = list.getId();
        createdAt = list.getCreatedAt();
    }

    private String title;
    private int listCount;
    private String id;
    private LocalDateTime createdAt;
}
