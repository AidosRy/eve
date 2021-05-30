package kz.balthazar.eve.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

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

    Long authorId;

    List<String> tags;

    List<String> categories;
}
