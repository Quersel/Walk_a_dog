package billennium.faculties.walkadog.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="${app.rest.wad-path}/walk/review")
public class WalkReviewController {

    @PostMapping("")
    public boolean template(){
        return true;
    }
}
