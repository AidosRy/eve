package kz.balthazar.eve.repository;

import kz.balthazar.eve.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    User findByEmailIgnoreCase(String emailId);
    List<User> findAllByFirstName(String name);
}
