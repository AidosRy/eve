package kz.balthazar.eve.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class EventDto {

    String title;

    String shortDescription;

    String longDescription;

    Double rating;

    Integer views;

    LocalDateTime date;

    String location;

    Integer price;

    Integer authorId;

    List<String> tags;

    List<String> categories;
}
