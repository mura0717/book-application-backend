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
public class BookListUpdateResponse {
    private String message;
    private boolean status;

    public BookListUpdateResponse(Booklist updatedBookList) {

    }
}
