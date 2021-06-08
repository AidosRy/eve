package kz.balthazar.eve.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
public class Event extends BaseEntity{

    String title;

    @Column(columnDefinition="TEXT")
    String shortDescription;

    @Column(columnDefinition="TEXT")
    String longDescription;

    Double rating = 0.0;

    Integer views;

    LocalDateTime date;

    String location;

    Integer price;

    Integer reviewsAmount = 0;

    String imageName;

    public Event(String title) {
        this.title = title;
    }

}
