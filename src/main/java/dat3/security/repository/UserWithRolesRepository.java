package dat3.security.repository;

import dat3.book_app.entity.Member;
import dat3.security.entity.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
UserWithRolesRepository extends JpaRepository<Member,String> {
    Boolean existsByEmail(String email);
}
