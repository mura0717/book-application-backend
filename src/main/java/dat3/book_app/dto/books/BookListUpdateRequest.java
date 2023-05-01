package dat3.book_app.dto.books;

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
    @JsonProperty("bookId")
    private String bookId = "";
    @JsonProperty("bookListId")
    private String bookListId = "";
}
