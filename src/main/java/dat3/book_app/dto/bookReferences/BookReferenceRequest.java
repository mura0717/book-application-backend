package dat3.book_app.dto.bookReferences;

import dat3.book_app.dto.books.BookMinimalResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookReferenceRequest {

    private List<BookReferenceResponse> books = new ArrayList<>();

    public BookReferenceRequest(List<BookReferenceResponse> books) {
        this.books = books;
    }
}
