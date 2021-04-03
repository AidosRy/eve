package kz.balthazar.eve.entity.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
public class UserLocation extends BaseEntity{

    private String country;

    private boolean enabled = false;

    @ManyToOne
    private User user;

    public UserLocation() {
        super();
        enabled = false;
    }

    public UserLocation(String country, User user) {
        super();
        this.country = country;
        this.user = user;
        enabled = false;
    }
}
