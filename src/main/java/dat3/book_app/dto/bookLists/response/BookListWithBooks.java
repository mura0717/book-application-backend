package dat3.book_app.dto.bookLists.response;

import dat3.book_app.entity.bookLists.Booklist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookListWithBooks {
    public BookListWithBooks(Booklist list, List<BookListBook> books) {
        title = list.getTitle();
        id = list.getId();
        this.books = books;
    }

    private List<BookListBook> books = new ArrayList<>();
    private String title;
    private String id;
}
