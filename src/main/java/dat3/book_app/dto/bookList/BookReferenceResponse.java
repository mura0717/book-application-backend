package dat3.book_app.dto.bookList;

import dat3.book_app.entity.Member;
import dat3.book_app.entity.bookLists.Booklist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookReferenceResponse {

    private List<String> bookReferences = new ArrayList<>();
    private String title;
    private Member member;

    public BookReferenceResponse(Booklist list) {
        title = list.getTitle();
        bookReferences = list.getBookReferences();
        member = list.getMember();
    }
}
