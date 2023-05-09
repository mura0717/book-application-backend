package dat3.book_app.dto.bookLists;

import dat3.book_app.entity.bookLists.Booklist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookListTitleResponse {
    public BookListTitleResponse(Booklist bookList) {
        this.title = bookList.getTitle();
    }

    public String title;
}
