package kz.balthazar.eve.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_table")
@Data
public class Role extends BaseEntity{

    @Column
    String name;
}
