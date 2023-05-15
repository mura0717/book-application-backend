package dat3.book_app.dto.bookLists.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookListUpdateRequest {
    private String bookId = "";
    private String bookListId = "";
    private String title = "";
    private String username = "";
}
