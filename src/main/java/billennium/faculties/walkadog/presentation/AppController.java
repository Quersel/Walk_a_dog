package billennium.faculties.walkadog.presentation;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${app.rest.wad-path}/registration")
@AllArgsConstructor
public class AppController {

    @GetMapping("")
    public String viewHomePage() {
        return "elo";
    }
}
