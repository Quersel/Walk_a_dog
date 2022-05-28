package billennium.faculties.walkadog.presentation;

import billennium.faculties.walkadog.application.WalkSlotsService;
import billennium.faculties.walkadog.application.dto.WalkSlotsDto;
import billennium.faculties.walkadog.domain.WalkSlots;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "${app.rest.wad-path}/slots")
@AllArgsConstructor
public class WalkSlotsController {
    private final WalkSlotsService walkSlotsService;

    @PostMapping("/add")
    public String addWalkSlot(@RequestBody WalkSlotsDto walkSlotsDto){
        return walkSlotsService.insertWalkSlots(walkSlotsDto);
    }
    @PostMapping("/delete")
    public String addWalkSlot(@RequestParam Long slotId){
        return walkSlotsService.deleteWalkSlots(slotId);
    }
    @GetMapping("")
    public List<WalkSlots> findAllWalks(@RequestParam Long trainerId){
        return walkSlotsService.readPresentWalkSlots(trainerId);
    }
}
