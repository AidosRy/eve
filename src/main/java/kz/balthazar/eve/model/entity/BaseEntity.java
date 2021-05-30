package kz.balthazar.eve.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class BaseEntity {
    @Id
    @GeneratedValue
    Long id;

    OffsetDateTime createdDate;

    @ManyToOne
    User createdUser;

    OffsetDateTime updatedDate;

    @ManyToOne
    User updatedUser;

    OffsetDateTime deletedDate;

    @ManyToOne
    User deletedUser;
}
