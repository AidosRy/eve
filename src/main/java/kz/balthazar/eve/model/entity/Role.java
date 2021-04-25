package kz.balthazar.eve.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role_table")
@Data
public class Role extends BaseEntity{

    @Column
    String name;
}
