package kz.balthazar.eve.controller;

import kz.balthazar.eve.model.dto.EventDto;
import kz.balthazar.eve.model.dto.ReviewDto;
import kz.balthazar.eve.model.entity.Review;
import kz.balthazar.eve.repository.EventRepo;
import kz.balthazar.eve.repository.ReviewRepo;
import kz.balthazar.eve.repository.UserRepo;
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

    @Autowired
    UserRepo userRepo;

    @GetMapping
    public List<Review> getReview(@RequestParam Long eventId) {
        return reviewRepo.findByEventId(eventId);
    }

    @PostMapping
    public String publishReview(ReviewDto dto) {
        reviewRepo.save(Review.builder()
                .id(dto.getId())
                .event(eventRepo.getOne(dto.getEventId()))
                .text(dto.getText())
                .rating(dto.getRating())
                .user(userRepo.getOne(dto.getUserId()))
                .build());
        return Response.ok;
    }
}
