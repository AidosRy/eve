package kz.balthazar.eve.service;

import kz.balthazar.eve.entity.model.Role;
import kz.balthazar.eve.entity.model.User;
import kz.balthazar.eve.repository.NewLocationTokenRepo;
import kz.balthazar.eve.repository.RoleRepo;
import kz.balthazar.eve.repository.UserLocationRepo;
import kz.balthazar.eve.repository.UserRepo;
import kz.balthazar.eve.util.Params;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {

    UserRepo userRepo;

    RoleRepo roleRepo;

    PasswordEncoder passwordEncoder;

    LoginAttemptService loginAttemptService;

//    DatabaseReader databaseReader;

    NewLocationTokenRepo newLocationTokenRepository;

    UserLocationRepo userLocationRepo;

//    @Bean
//    public DatabaseReader databaseReader() throws IOException, GeoIp2Exception {
//        File resource = new File("src/main/resources/GeoLite2-Country.mmdb");
//        return new DatabaseReader.Builder(resource).build();
//    }

//    public void addUserLocation(User user, String ip) throws IOException, GeoIp2Exception {
//        InetAddress ipAddress = InetAddress.getByName(ip);
//        String country
//                = databaseReader.country(ipAddress).getCountry().getName();
//        UserLocation loc = new UserLocation(country, user);
//        loc.setEnabled(true);
//        loc = userLocationRepo.save(loc);
//    }
//
//    public NewLocationToken isNewLoginLocation(String username, String ip) {
//        try {
//            InetAddress ipAddress = InetAddress.getByName(ip);
//            String country
//                    = databaseReader.country(ipAddress).getCountry().getName();
//            User user = userRepo.findByEmailIgnoreCase(username);
//            UserLocation loc = userLocationRepo.findByCountryAndUser(country, user);
//            if ((loc == null) || !loc.isEnabled()) {
//                return createNewLocationToken(country, user);
//            }
//        } catch (Exception e) {
//            return null;
//        }
//        return null;
//    }

//    private NewLocationToken createNewLocationToken(String country, User user) {
//        UserLocation loc = new UserLocation(country, user);
//        loc = userLocationRepo.save(loc);
//        NewLocationToken token = new NewLocationToken(UUID.randomUUID().toString(), loc);
//        return newLocationTokenRepository.save(token);
//    }

    public User saveUser(User user) {
        Role userRole = roleRepo.findByName("ROLE_USER");
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }



    public User findByLogin(String login) {
        Optional<User> user = userRepo.findByLogin(login);
        return user.orElse(null);
    }

    public User uploadFile(Long userId, byte[] file) {
        User user = userRepo.findById(userId).get();
        user.setPhoto(file);
        return userRepo.save(user);
    }

    public User findByLoginAndPassword(String login, String password) {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip))
            throw new RuntimeException(Params.ipBlocked);
//        if (isNewLoginLocation(login, ip) == null)
//            throw new RuntimeException(Params.unusualLogin);
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                loginAttemptService.loginSucceeded(ip);
                return user;
            }
        }
        loginAttemptService.loginFailed(ip);
        return null;
    }

    private String getClientIP() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}
