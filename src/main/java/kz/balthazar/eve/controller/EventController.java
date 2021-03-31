package kz.balthazar.eve.controller;

import kz.balthazar.eve.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event/{eventId}")
public class EventController {

    @Autowired
    EventService service;

    @PostMapping
    public String attendEvent(@PathVariable Long eventId, Long userId) {

        return "post";
    }

    @GetMapping
    public String getEvent(@PathVariable Long eventId) {
        return "get";
    }

    @DeleteMapping
    public String unAttendEvent(@PathVariable Long eventId) {
        return "delete";
    }

//    @PatchMapping
//    public String
}
