package kz.balthazar.eve;

import kz.balthazar.eve.model.entity.Event;
import kz.balthazar.eve.model.entity.Role;
import kz.balthazar.eve.model.entity.User;
import kz.balthazar.eve.repository.EventRepo;
import kz.balthazar.eve.repository.RoleRepo;
import kz.balthazar.eve.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Eve {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    EventRepo eventRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Eve.class, args);
    }

    @PostConstruct
    public void init() {

        Role adminRole = new Role();
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepo.save(userRole);
        adminRole.setName("ROLE_ADMIN");
        roleRepo.save(adminRole);

        User user = new User();
        user.setEmail("qaidosp@gmail.com");
        user.setPassword(passwordEncoder.encode("asdasd"));
        user.setEnabled(true);
        user.setRole(adminRole);
        user.setLogin("asdasd");
        userRepo.save(user);
        String s = "s";
        for(int i=0; i<3; i++) {
            user = new User();
            user.setFirstName(s+=s);
            user.setEmail("qaidosp@gmail.com"+s);
            user.setPassword(passwordEncoder.encode("asdasd"));
            user.setEnabled(true);
            user.setRole(adminRole);
            user.setLogin(s);
            userRepo.save(user);
        }

        for(int i=0; i<13; i++) {
            Event event = new Event();
            event.setRating((double) i);
            eventRepo.save(event);
        }
    }
}
