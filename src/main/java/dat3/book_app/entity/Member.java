package dat3.book_app.entity;

import dat3.book_app.entity.googleBooks.Booklist;
import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Member extends UserWithRoles {

    public Member(String user, String password, String email) {
        super(user, password, email);
    }

    @OneToMany(mappedBy = "member")
    private List<Booklist> booklists = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();
}
