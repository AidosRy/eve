package kz.balthazar.eve;

import kz.balthazar.eve.model.entity.Event;
import kz.balthazar.eve.model.entity.Role;
import kz.balthazar.eve.model.entity.User;
import kz.balthazar.eve.recommender.Recommender;
import kz.balthazar.eve.repository.EventRepo;
import kz.balthazar.eve.repository.RoleRepo;
import kz.balthazar.eve.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

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

    @Autowired
    Recommender recommender;

    String[] lorem = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris porta in arcu at luctus. Praesent vel libero ut justo aliquet euismod id rhoncus felis. Nulla nec fringilla urna. Curabitur lectus felis, tincidunt feugiat risus in, rutrum rhoncus urna. Nullam eu tincidunt nulla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Duis sit amet diam lacinia, pretium dui sed, vestibulum tellus. Fusce rutrum, eros sit amet fermentum pulvinar, magna ipsum lobortis nisi, ac congue mi felis nec enim. Curabitur fermentum urna rutrum porta commodo. Sed lectus tortor, tempor ut tincidunt a, maximus eu nulla. Ut sit amet sagittis eros, eu sodales sem. Vivamus id magna lectus. Pellentesque interdum lobortis mi sed laoreet. Proin luctus odio a imperdiet ultricies. Proin elementum ac lorem vitae ultricies. Nulla sollicitudin quam at tellus finibus, vitae condimentum lectus scelerisque. Duis feugiat euismod ex vel pretium. Aenean et urna sed quam pretium blandit luctus eu velit. In tristique, nibh nec vehicula egestas, metus magna pretium dolor, in pretium est massa vel justo. Sed vel dapibus ipsum. Proin suscipit nisl at tellus tincidunt sagittis. Cras magna dui, ornare quis feugiat vehicula, volutpat ac purus. Curabitur vehicula est luctus metus bibendum, ut placerat nibh luctus. Phasellus molestie justo eget dictum facilisis. Etiam vulputate justo ac urna ullamcorper, ac sagittis dolor volutpat. Ut vel metus a enim vehicula viverra. Proin sit amet tempor urna. Aenean turpis sapien, mollis a commodo at, fermentum et eros. Nulla facilisi. Nullam vulputate varius urna vel efficitur. Mauris risus erat, vestibulum vel venenatis efficitur, hendrerit sit amet ipsum. Quisque orci tellus, auctor vel consequat sed, consequat laoreet felis. Quisque nunc nulla, maximus vel urna id, vestibulum feugiat est.Sed euismod tortor diam. In suscipit pharetra egestas. Aliquam in libero nec mi maximus maximus. In nisi nisi, pretium ut tortor sed, blandit ultricies eros. Donec vitae ullamcorper urna, nec elementum urna. Cras ac placerat ipsum. Donec sodales, dolor in accumsan posuere, ligula turpis mollis sapien, id faucibus nisl eros vitae enim. Duis tincidunt nisi ac tortor semper porta. Etiam interdum leo eu mattis posuere. Donec tincidunt felis consectetur viverra sollicitudin. Sed pulvinar lobortis enim, eu vehicula diam eleifend viverra. Aliquam a libero mi. Vivamus sed porttitor orci. Fusce libero est, consequat ut malesuada in, convallis volutpat diam. Donec ultrices pulvinar lacus, et posuere elit gravida sit amet. Nam urna erat, tincidunt tempus massa nec, rutrum euismod sem. Donec sed elit nec velit mollis mattis id sodales est. Sed eget interdum mi. Sed dictum dolor orci, fermentum tempus diam tincidunt elementum.
            """.split(" ");

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
            double descriptionSize = Math.random()*418;
            StringBuilder longDesc = new StringBuilder();
            for(int y = 0; y < descriptionSize; y++) {
                longDesc.append(lorem[y]).append(" ");
            }

            Event event = new Event();
            event.setLongDescription(longDesc.toString());
            double titleSize = Math.random()*30;
            event.setTitle(longDesc.substring(0, (int) titleSize));//exception
            double shortDescriptionSize = Math.random()*30;
            StringBuilder shortDesc = new StringBuilder();
            for(int y = 0; y < shortDescriptionSize; y++) {
                shortDesc.append(lorem[y]).append(" ");
            }
            event.setShortDescription(shortDesc.toString());
            event.setDate(LocalDateTime.now());
            event.setRating(Math.random() * 5);
            event.setViews((int) (Math.random() * 20000));
            eventRepo.save(event);
        }
        recommender.start();

    }
}
