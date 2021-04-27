package kz.balthazar.eve.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
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

    Double rating;

    Integer views;

    OffsetDateTime date;

    Integer price;

    @ManyToMany
    List<Category> categories = new ArrayList<>();

    public void addAttendee(User user) {
        attendees.add(user);
    }

    public void deleteAttendee(User user) {
        attendees.remove(user);
    }
}
