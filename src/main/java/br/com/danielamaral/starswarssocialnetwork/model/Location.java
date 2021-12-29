package br.com.danielamaral.starswarssocialnetwork.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.danielamaral.starswarssocialnetwork.dto.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double latitude;
    private double longitude;
    private String galaxy;

    public static Location parseLocation(LocationDto locationDto) {
        return new Location(locationDto.getId(), locationDto.getLatitude(), locationDto.getLongitude(), locationDto.getGalaxy());
    }

}
