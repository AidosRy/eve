package kz.balthazar.eve.repository;

import kz.balthazar.eve.entity.model.NewLocationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewLocationTokenRepo extends JpaRepository<NewLocationToken, Long> {
}
