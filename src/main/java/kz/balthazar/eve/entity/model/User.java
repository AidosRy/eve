package kz.balthazar.eve.entity.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "user_table")
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

    @OneToMany
    List<Event> attendedEvents;

    @OneToMany
    List<Event> likedEvents;
}
