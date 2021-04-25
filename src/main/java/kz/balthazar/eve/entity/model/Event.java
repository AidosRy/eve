package kz.balthazar.eve.entity.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Event extends BaseEntity{

    String name;

    String shortDescription;

    String longDescription;

    @ManyToMany
    List<User> attendees = new ArrayList<>();

    @ManyToMany
    List<Tag> tags = new ArrayList<>();

    @Lob
    byte[] thumbnail;

    double rating;

    @ManyToMany
    Set<Category> categories;

    public void addAttendee(User user) {
        attendees.add(user);
    }

    public void deleteAttendee(User user) {
        attendees.remove(user);
    }
}
