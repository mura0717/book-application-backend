package dat3.security.repository;

import dat3.book_app.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface
MemberRepository extends JpaRepository<Member,String> {
    Optional<Member> findByUsernameLike(@NonNull String username);
    Boolean existsByEmail(String email);
}
