package dat3.book_app.dto.bookLists.response;

import dat3.book_app.entity.bookLists.Booklist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookListsTitleResponse {
    public BookListsTitleResponse(Booklist bookList) {
        title = bookList.getTitle();
        id = bookList.getId();
    }

    private String title;
    private String id;
}
