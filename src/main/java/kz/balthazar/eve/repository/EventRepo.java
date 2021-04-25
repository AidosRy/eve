package kz.balthazar.eve.repository;

import kz.balthazar.eve.entity.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Long> {
}
