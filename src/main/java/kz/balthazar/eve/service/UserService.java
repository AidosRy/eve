package kz.balthazar.eve.service;

import kz.balthazar.eve.entity.model.Role;
import kz.balthazar.eve.entity.model.User;
import kz.balthazar.eve.repository.RoleRepo;
import kz.balthazar.eve.repository.UserRepo;
import kz.balthazar.eve.security.BruteForceService;
import kz.balthazar.eve.util.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private BruteForceService bruteForceService;

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
        if (bruteForceService.isBlocked(ip))
            throw new RuntimeException(Errors.ipBlocked);
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                bruteForceService.loginSucceeded(ip);
                return user;
            }
        }
        bruteForceService.loginFailed(ip);
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
