package kz.balthazar.eve.controller;

import kz.balthazar.eve.model.entity.Review;
import kz.balthazar.eve.repository.EventRepo;
import kz.balthazar.eve.repository.ReviewRepo;
import kz.balthazar.eve.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    EventRepo eventRepo;

    @GetMapping
    public List<Review> getReview(@RequestParam Long eventId) {
        return reviewRepo.findByEventId(eventId);
    }

    @PostMapping
    public String publishReview(@RequestParam Long eventId) {
        reviewRepo.save(Review.builder().event(eventRepo.getOne(eventId)).build());
        return Response.ok;
    }
}
