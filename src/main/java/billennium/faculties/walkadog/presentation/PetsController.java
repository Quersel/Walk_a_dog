package billennium.faculties.walkadog.presentation;

import billennium.faculties.walkadog.application.PetsService;
import billennium.faculties.walkadog.application.dto.PetsDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${app.rest.wad-path}/pets")
@AllArgsConstructor
public class PetsController {

    private final PetsService petsService;

    @PostMapping("/add")
    public void createPet(@RequestBody PetsDto petsDto) {
        petsService.createPet(petsDto);
    }

    @GetMapping("/pet")
    public PetsDto getPetById(@RequestParam Long petId) {
        return petsService.findPetById(petId);
    }

    @GetMapping("")
    public List<PetsDto> getAllPetsFromUser(@RequestParam Long userId) {
        return petsService.getAllPetsFromUser(userId);
    }

    @PostMapping("/archive")
    public void archivePet(@RequestParam Long petId) {
        petsService.archivePet(petId);
    }

    @PostMapping("update")
    public void updatePetById(@RequestBody PetsDto petsDto, @RequestParam Long petId) {
        petsService.updatePet(petsDto, petId);
    }
}
