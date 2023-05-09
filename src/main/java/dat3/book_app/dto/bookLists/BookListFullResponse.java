package dat3.book_app.dto.bookLists;

import dat3.book_app.dto.books.BookMinimalResponse;
import dat3.book_app.entity.bookLists.Booklist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookListFullResponse {
    public BookListFullResponse(Booklist list, List<BookMinimalResponse> books) {
        title = list.getTitle();
        listCount = list.getBookReferences().size();
        id = list.getId();
        createdAt = list.getCreatedAt();
        updatedAt = list.getLastEdited();
        this.books = books;
    }

    private List<BookMinimalResponse> books = new ArrayList<>();
    private String title;
    private int listCount;
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
