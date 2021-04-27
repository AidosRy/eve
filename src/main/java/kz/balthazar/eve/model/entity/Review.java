package kz.balthazar.eve.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
public class Review extends BaseEntity{

    @OneToOne
    User user;

    String text;

    double rating;

    @OneToOne
    Event event;

}
