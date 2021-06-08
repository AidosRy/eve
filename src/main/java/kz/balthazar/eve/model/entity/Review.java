package kz.balthazar.eve.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class Review extends BaseEntity{

    @ManyToOne
    User user;

    String text;

    double rating;

    @ManyToOne
    Event event;

}
