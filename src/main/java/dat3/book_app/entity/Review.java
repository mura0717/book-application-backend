package dat3.book_app.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review extends Entities {
    private String bookReference;
    private int stars;
    private Member user;
    private String comment;

}
