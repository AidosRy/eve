package kz.balthazar.eve.controller;

import kz.balthazar.eve.model.entity.Event;
import kz.balthazar.eve.model.entity.Review;
import kz.balthazar.eve.recommender.Recommender;
import kz.balthazar.eve.repository.EventRepo;
import kz.balthazar.eve.repository.ReviewRepo;
import kz.balthazar.eve.repository.UserRepo;
import kz.balthazar.eve.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/event")
@CrossOrigin
public class EventUserController {

    @Autowired
    EventService service;

    @Autowired
    EventRepo eventRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    EntityManager entityManager;

    @Autowired
    Recommender recommender;

    @Autowired
    ReviewRepo reviewRepo;

    @PostMapping("/attend")
    public ResponseEntity<String> attendEvent(@RequestParam Long eventId, @RequestParam Long userId) {
        Event event = eventRepo.getOne(eventId);
        event.addAttendee(userRepo.getOne(userId));
        eventRepo.save(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{eventId}")
    public Event getEvent(@PathVariable Long eventId) {
        return eventRepo.findById(eventId).get();
    }

    @DeleteMapping("/unattend")
    public ResponseEntity<String> unAttendEvent(@RequestParam Long eventId, @RequestParam Long userId) {
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

    @GetMapping("/recommended")
    public List<Event> getRecommended(@RequestParam Long userId) {
        List<Event> events = new ArrayList<>();
        Stream<Map.Entry<Event, Double>> stream = recommender
                .start()
                .get(userRepo
                        .findById(userId)
                        .get())
                .entrySet()
                .stream()
                .sorted(Collections
                        .reverseOrder(Map
                                .Entry
                                .comparingByValue()));
        stream.forEach(e -> events.add(e.getKey()));
        return events;
    }

    @GetMapping("/reviews")
    public List<Review> reviews(@RequestParam Long eventId) {
        return reviewRepo.findByEventId(eventId);
    }

}
