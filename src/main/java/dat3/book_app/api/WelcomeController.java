package dat3.book_app.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping
public class WelcomeController {
    @GetMapping
    public RedirectView welcome()
    {
        return new RedirectView("/swagger-ui/index.html");
    }
}
