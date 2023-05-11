package dat3.book_app.dto.bookLists.response;

import dat3.book_app.entity.bookLists.Booklist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookListCreateResponse {
    public BookListCreateResponse(String message, int listCount, boolean status, Booklist booklist) {
        id = booklist.getId();
        title = booklist.getTitle();
        this.listCount = listCount;
        this.message = message;
        this.status = status;
    }

    public BookListCreateResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    private int listCount;

    private String message;
    private boolean status;
    private String title;
    private String id;
}
