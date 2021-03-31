package kz.balthazar.eve.entity.model;

import javax.persistence.ManyToOne;

public class Review extends BaseEntity{

    @ManyToOne
    User user;

    String text;

    double rating;


}
