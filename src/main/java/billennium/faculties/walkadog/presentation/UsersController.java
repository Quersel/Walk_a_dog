package billennium.faculties.walkadog.presentation;

import billennium.faculties.walkadog.application.RegistrationService;
import billennium.faculties.walkadog.application.dto.RegistrationRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "${app.rest.wad-path}/users")
@AllArgsConstructor
public class UsersController {
    private final RegistrationService registrationService;

    @PostMapping("/registration")
    @CrossOrigin(origins = "http://localhost:3000")
    public String register(@RequestBody RegistrationRequestDto request) {
        return registrationService.register(request);
    }

    @GetMapping("/registration/confirm")
    public String confirmAccount(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @GetMapping("/registration/resend")
    public String resendEmail(@RequestParam("email") String email) {
        return registrationService.resendActivationEmail(email);
    }


}