package kz.balthazar.eve.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageModel extends BaseEntity{
    private String name;
    private String type;
    @Lob
    @Column(columnDefinition="bytea")
    private byte[] picture;

}