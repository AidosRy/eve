package kz.balthazar.eve.controller;

import kz.balthazar.eve.model.entity.Event;
import kz.balthazar.eve.model.entity.User;
import kz.balthazar.eve.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileUserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/my_events")
    public List<Event> myEvents(@RequestParam Long id) {
        return userRepo.findById(id).get().getAttendedEvents();
    }

    @GetMapping("/profile")
    public User myProfile(@RequestParam Long id) {
        return userRepo.findById(id).get();
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestBody User user) {
        userRepo.save(user);
    }

}
