package kz.balthazar.eve.controller;

import kz.balthazar.eve.model.entity.Event;
import kz.balthazar.eve.repository.EventRepo;
import kz.balthazar.eve.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@CrossOrigin
public class EventAdminController {

    @Autowired
    EventRepo eventRepo;

    @PostMapping
    public String addEvent(@RequestBody Event event) {
        eventRepo.save(event);
        return Response.success;
    }

    @DeleteMapping
    public String deleteEvent(Long id) {
        eventRepo.deleteById(id);
        return Response.success;
    }

    @PutMapping
    public String updateEvent(@RequestBody Event event) {
        eventRepo.save(event);
        return Response.success;
    }
}
