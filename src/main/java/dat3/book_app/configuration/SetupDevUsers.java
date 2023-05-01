package dat3.book_app.configuration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import static dat3.security.config.UsersForDevelopmentOnly.setupUserWithRoleUsers;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    PasswordEncoder passwordEncoder;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, PasswordEncoder passwordEncoder) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Remove me before production
    @Override
    public void run(ApplicationArguments args) {
        setupUserWithRoleUsers(userWithRolesRepository, passwordEncoder);
    }
}
