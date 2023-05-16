package dat3.book_app.entity.bookLists;

import dat3.book_app.entity.Entities;
import dat3.book_app.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booklist extends Entities {
    @Column(name = "book_references")
    @ElementCollection()
    private List<String> bookReferences = new ArrayList<>();

    @Column(name = "listTitle")
    private String title;

    @ManyToOne
    private Member member;


    public Booklist(String listTitle) {

    }
}

