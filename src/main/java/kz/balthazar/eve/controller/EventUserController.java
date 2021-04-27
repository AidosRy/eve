package kz.balthazar.eve.controller;

import kz.balthazar.eve.model.entity.Event;
import kz.balthazar.eve.repository.EventRepo;
import kz.balthazar.eve.repository.UserRepo;
import kz.balthazar.eve.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.*;

@RestController
@RequestMapping("/event")
public class EventUserController {

    @Autowired
    EventService service;

    @Autowired
    EventRepo eventRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    EntityManager entityManager;

    @PostMapping("/attend")
    public ResponseEntity<String> attendEvent(@PathVariable Long eventId, Long userId) {
        Event event = eventRepo.getOne(eventId);
        event.addAttendee(userRepo.getOne(userId));
        eventRepo.save(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public Event getEvent(@PathVariable Long eventId) {
        return eventRepo.findById(eventId).get();
    }

    @DeleteMapping("/unattend")
    public ResponseEntity<String> unAttendEvent(@PathVariable Long eventId, Long userId) {
        Event event = eventRepo.getOne(eventId);
        event.deleteAttendee(userRepo.getOne(userId));
        eventRepo.save(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/popular")
    public List getPopular(@RequestParam String sort, @RequestParam String order) {
        String sql = "SELECT e FROM Event e ORDER BY e." + sort + " " + order;
        return entityManager.createQuery(sql).getResultList();
    }

}
