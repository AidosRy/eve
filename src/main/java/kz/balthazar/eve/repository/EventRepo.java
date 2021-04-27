package kz.balthazar.eve.repository;

import kz.balthazar.eve.model.entity.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e ORDER BY e.rating")
    List<Event> findPopular(Pageable pageable);

    List<Event> findAllByOrderByRatingAsc();

    List<Event> findAllByOrderByRatingDesc();

    List<Event> findAllByOrderByDateAsc();

    List<Event> findAllByOrderByDateDesc();

    List<Event> findAllByOrderByPriceAsc();

    List<Event> findAllByOrderByPriceDesc();

    List<Event> findAllByOrderByViewsAsc();

    List<Event> findAllByOrderByViewsDesc();
}