package billennium.faculties.walkadog.application;

import billennium.faculties.walkadog.domain.ConfirmationToken;
import billennium.faculties.walkadog.domain.Users;
import billennium.faculties.walkadog.infrastructure.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USER_NOT_FOUND = "User with %s was not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usersRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(Users user) {
        boolean userExists = usersRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("Email already taken");
            //TODO RESEND ACTIVACTION EMAIL
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        usersRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .token(token)
                .expiresAt(LocalDateTime.now().plusMinutes(30))
                .createdAt(LocalDateTime.now())
                .users(user)
                .build();
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public String resendActivationLink(String email){
        Users user = usersRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("Email not found"));
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .token(token)
                .expiresAt(LocalDateTime.now().plusMinutes(30))
                .createdAt(LocalDateTime.now())
                .users(user)
                .build();
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void enableUser(String email){
        Users user = usersRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("Wrong email"));
        user.setEnabled(true);
    }

}
