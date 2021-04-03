package kz.balthazar.eve.repository;

import kz.balthazar.eve.entity.model.User;
import kz.balthazar.eve.entity.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepo extends JpaRepository<UserLocation, Long> {
    UserLocation findByCountryAndUser(String country, User user);
}
