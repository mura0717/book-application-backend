package dat3.book_app.dto.bookLists;

import dat3.book_app.entity.Member;
import dat3.book_app.entity.bookLists.Booklist;
import dat3.book_app.service.bookLists.BookLists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookListCreateRequest {
    public Booklist toBookList(Member member){
        var bookList = new Booklist();
        bookList.setTitle(title);
        bookList.setMember(member);
        return bookList;
    }

    private String title;
}
