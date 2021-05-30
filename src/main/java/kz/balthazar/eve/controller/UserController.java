package kz.balthazar.eve.controller;

import kz.balthazar.eve.model.entity.User;
import kz.balthazar.eve.repository.UserRepo;
import kz.balthazar.eve.service.UserService;
import kz.balthazar.eve.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public User submit(MultipartFile file, Long userId) throws IOException {
        return userService.uploadFile(userId, file.getBytes());
    }

    @GetMapping("/friends")
    public List<User> getFriends(Long userId) {
        return userRepo.findById(userId).get().getFriends();
    }

    @PostMapping("/add_friend")
    public String addFriend(Long userId, Long friendId) {
        userRepo.findById(userId).get().addFriend(userRepo.getOne(friendId));
        return Response.ok;
    }
}
