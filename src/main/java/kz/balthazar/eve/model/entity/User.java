package kz.balthazar.eve.model.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    boolean showType;

    String firstName;

    String lastName;

    boolean isEnabled;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @ManyToMany
    List<Event> attendedEvents;

//    @OneToMany
//    List<Event> likedEvents;

    public User(String login) {
        this.login = login;
    }
}
