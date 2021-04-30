package kz.balthazar.eve.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageModel extends BaseEntity{
    private String name;
    private String type;
    @Column(name = "picture", length = 1000)
    private byte[] picture;
}