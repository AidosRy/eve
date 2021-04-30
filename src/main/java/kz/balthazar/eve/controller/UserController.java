package kz.balthazar.eve.controller;

import kz.balthazar.eve.model.entity.User;
import kz.balthazar.eve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public User submit(MultipartFile file, Long userId) throws IOException {
        return userService.uploadFile(userId, file.getBytes());
    }
}
