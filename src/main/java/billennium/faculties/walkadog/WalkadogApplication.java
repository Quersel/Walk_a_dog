package billennium.faculties.walkadog;

import billennium.faculties.walkadog.application.UsersService;
import billennium.faculties.walkadog.domain.Users;
import billennium.faculties.walkadog.infrastructure.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WalkadogApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WalkadogApplication.class, args);
    }

    @Autowired
    private UsersService usersService;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WalkadogApplication.class);
    }


    public void run(String... args) throws Exception {
        Users users = new Users();
        users.setName("Spring Microservices in Action");
        users.setLastName("John Carnell");
        users.setEmail("test@test.test");
        users.setPassword("test");
        users.setPhoneNumber("333444555");

    }

}
