package billennium.faculties.walkadog.presentation;

import billennium.faculties.walkadog.application.WalkService;
import billennium.faculties.walkadog.application.dto.WalkDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${app.rest.wad-path}/walks")
@AllArgsConstructor
public class WalkController {
    private final WalkService walkService;

    @PostMapping("/add")
    public String addWalk(@RequestBody WalkDto walkDto) {
        return walkService.insertWalk(walkDto);
    }

    @GetMapping("/delete")
    public String deleteWalk(@RequestParam Long walkId) {
        return walkService.deleteWalk(walkId);
    }

    @GetMapping("/start")
    public String startWalk(@RequestParam Long walkId) {
        return walkService.startWalk(walkId);
    }

    @GetMapping("/cancel")
    public String cancelWalk(@RequestParam Long walkId) {
        return walkService.cancelWalk(walkId);
    }

    @GetMapping("/finish")
    public String finishWalk(@RequestParam Long walkId) {
        return walkService.finishWalk(walkId);
    }
}
