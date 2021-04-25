package kz.balthazar.eve.controller;

import kz.balthazar.eve.entity.model.Event;
import kz.balthazar.eve.entity.model.User;
import kz.balthazar.eve.repository.EventRepo;
import kz.balthazar.eve.repository.UserRepo;
import kz.balthazar.eve.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventUserController {

    @Autowired
    EventService service;

    @Autowired
    EventRepo eventRepo;

    @Autowired
    UserRepo userRepo;

    @PostMapping
    public ResponseEntity<String> attendEvent(@PathVariable Long eventId, Long userId) {
        Optional<Event> event = eventRepo.findById(eventId);
        event.ifPresent(value -> value.addAttendee(userRepo.findById(userId).get()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public Event getEvent(@PathVariable Long eventId) {
        return eventRepo.findById(eventId).get();
    }

    @DeleteMapping
    public ResponseEntity<String> unAttendEvent(@PathVariable Long eventId, Long userId) {
        Optional<Event> event = eventRepo.findById(eventId);
        event.ifPresent(value -> value.deleteAttendee(userRepo.findById(userId).get()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/popular")
//    public String getPopular() {
//
//    }

}
