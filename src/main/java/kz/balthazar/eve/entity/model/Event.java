package kz.balthazar.eve.entity.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Event extends BaseEntity{

    String name;

    String shortDescription;

    String longDescription;

    @ManyToMany
    Set<User> attendees;

    @ManyToMany
    Set<Tag> tags;

    @Lob
    byte[] thumbnail;

    double rating;

    @ManyToMany
    Set<Category> categories;


}
