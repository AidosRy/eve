package kz.balthazar.eve.model.entity;

import javax.persistence.ManyToOne;

public class Review extends BaseEntity{

    @ManyToOne
    User user;

    String text;

    double rating;


}
