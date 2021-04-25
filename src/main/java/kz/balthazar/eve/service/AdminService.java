package kz.balthazar.eve.service;

import kz.balthazar.eve.model.entity.User;
import kz.balthazar.eve.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public List<User> getUsersByName(String name) {
        return userRepo.findAllByFirstName(name);
    }

    public User getUser(String login) {
        Optional<User> user = userRepo.findByLogin(login);
        return user.orElse(null);
    }

    public User getUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.orElse(null);
    }
}
