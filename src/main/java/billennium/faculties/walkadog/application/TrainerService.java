package billennium.faculties.walkadog.application;

import billennium.faculties.walkadog.infrastructure.TrainerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrainerService implements UserDetailsService {
    private final TrainerRepository trainerRepository;
    private static final String USER_NOT_FOUND = "User with email %s was not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return trainerRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }
}
