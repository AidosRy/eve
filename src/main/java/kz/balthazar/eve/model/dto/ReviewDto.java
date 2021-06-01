package kz.balthazar.eve.model.dto;

import lombok.Getter;

@Getter
public class ReviewDto {

    Long userId;

    String text;

    double rating;

    Long eventId;
}
