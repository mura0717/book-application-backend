package dat3.book_app.dto.bookLists;

import dat3.book_app.entity.bookLists.Booklist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookReferencesTitleRespons {
    public BookReferencesTitleRespons(Booklist bookList) {
        title = bookList.getTitle();
        id = bookList.getId();
    }

    private String title;
    private String id;
}
