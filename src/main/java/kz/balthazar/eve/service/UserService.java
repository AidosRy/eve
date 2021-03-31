package kz.balthazar.eve.service;

import kz.balthazar.eve.entity.model.Role;
import kz.balthazar.eve.entity.model.User;
import kz.balthazar.eve.repository.RoleRepo;
import kz.balthazar.eve.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

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
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

}
