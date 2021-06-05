package kz.balthazar.eve.model.dto;

import lombok.Getter;

@Getter
public class ReviewDto {

    Long id;

    Long userId;

    String text;

    double rating;

    Long eventId;
}
