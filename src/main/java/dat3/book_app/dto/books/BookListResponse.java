package dat3.book_app.dto.books;

import dat3.book_app.entity.Booklist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookListResponse {
    public BookListResponse(Booklist list) {
        references = list.getBookReferences();
        title = list.getTitle();
        listCount = list.getBookReferences().size();
        id = list.getId();
    }

    private List<String> references = new ArrayList<>();
    private String title;
    private int listCount;
    private String id;
}
