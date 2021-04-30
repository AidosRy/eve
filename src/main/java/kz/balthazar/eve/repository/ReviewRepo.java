package kz.balthazar.eve.repository;

import kz.balthazar.eve.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findByEventId(Long eventId);
}
