package kz.balthazar.eve.controller;

import kz.balthazar.eve.model.entity.Event;
import kz.balthazar.eve.repository.EventRepo;
import kz.balthazar.eve.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
        return Response.ok;
    }

    @DeleteMapping
    public String deleteEvent(Long id) {
        eventRepo.deleteById(id);
        return Response.ok;
    }

    @PutMapping
    public String updateEvent(@RequestBody Event event) {
        eventRepo.save(event);
        return Response.ok;
    }
}
