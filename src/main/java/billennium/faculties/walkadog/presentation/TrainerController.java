package billennium.faculties.walkadog.presentation;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/${app.rest.wad-path}/trainers")
@AllArgsConstructor
public class TrainerController {

}
