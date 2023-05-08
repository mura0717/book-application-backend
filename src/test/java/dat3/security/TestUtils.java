package dat3.security;

import dat3.book_app.entity.Member;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestUtils {

  public static void setupTestUsers(PasswordEncoder encoder, UserWithRolesRepository userWithRolesRepository){
    userWithRolesRepository.deleteAll();
    String passwordUsedByAll = encoder.encode("secret");
    var user1 = new Member("u1", passwordUsedByAll, "u1@a.dk");
    var user2 = new Member("u2", passwordUsedByAll, "u2@a.dk");
    var user3 = new Member("u3", passwordUsedByAll, "u3@a.dk");
    var userNoRoles = new Member("u4", passwordUsedByAll, "u4@a.dk");
    user1.addRole(Role.USER);
    user1.addRole(Role.ADMIN);
    user2.addRole(Role.USER);
    user3.addRole(Role.ADMIN);
    userWithRolesRepository.save(user1);
    userWithRolesRepository.save(user2);
    userWithRolesRepository.save(user3);
    userWithRolesRepository.save(userNoRoles);
  }
}
