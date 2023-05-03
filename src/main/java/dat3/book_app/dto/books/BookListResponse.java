package dat3.book_app.dto.books;

import dat3.book_app.entity.Booklist;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class BookListResponse {
    public BookListResponse(Booklist list) {
        references = list.getBookReferences();
        title = list.getTitle();
        Id = list.getId();
    }

    private final List<String> references;
    private final String Id;
    private final String title;
}
