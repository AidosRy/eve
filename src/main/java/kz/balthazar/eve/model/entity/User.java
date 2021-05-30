package kz.balthazar.eve.model.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@Data
public class User extends BaseEntity{

    @NotNull
    String login;

    @NotNull
    String password;

    @NotNull
    String email;

    String bio;

    @Lob
    byte[] photo;

    String telephone;

    String firstName;

    String lastName;

    boolean isEnabled;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @ManyToMany
    List<User> friends;

    @ManyToMany
    List<ImageModel> images;

    @ManyToMany
    List<Event> attendedEvents = new ArrayList<>();

//    @OneToMany
//    List<Event> likedEvents;

    public User(String login) {
        this.login = login;
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }

    public void addAttendedEvents(Event event) {
        attendedEvents.add(event);
    }

    public void deleteAttendedEvent(Event event) {
        attendedEvents.remove(event);
    }
}
