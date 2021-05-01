package kz.balthazar.eve.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class Event extends BaseEntity{

    String title;

    @Column(columnDefinition="TEXT")
    String shortDescription;

    @Column(columnDefinition="TEXT")
    String longDescription;
//
//    @ManyToMany
//    List<User> attendees = new ArrayList<>();

    @ManyToMany
    List<Tag> tags = new ArrayList<>();

    @Lob
    byte[] thumbnail;

    Double rating;

    Integer views;

    LocalDateTime date;

    String location;

    Integer price;

    @ManyToOne
    User author;

    @ManyToMany
    List<Category> categories = new ArrayList<>();

    public Event(String title) {
        this.title = title;
    }

//    public void addAttendee(User user) {
//        attendees.add(user);
//    }
//
//    public void deleteAttendee(User user) {
//        attendees.remove(user);
//    }
}
