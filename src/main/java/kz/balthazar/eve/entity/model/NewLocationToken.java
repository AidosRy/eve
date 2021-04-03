package kz.balthazar.eve.entity.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class NewLocationToken extends BaseEntity{

    private String token;

    @OneToOne(targetEntity = UserLocation.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_location_id")
    private UserLocation userLocation;
}