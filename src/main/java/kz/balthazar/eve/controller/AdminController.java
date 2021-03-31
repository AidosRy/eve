package kz.balthazar.eve.controller;

import kz.balthazar.eve.entity.model.User;
import kz.balthazar.eve.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/users")
    public Object getUsers(@RequestParam(required = false) String login,
                           @RequestParam(required = false) String name) {
        if(login != null)
            return adminService.getUser(login);
        else if(name != null)
            return adminService.getUsersByName(name);
        return adminService.getAllUsers();
    }

//    @GetMapping("/event")
//    public String
}
