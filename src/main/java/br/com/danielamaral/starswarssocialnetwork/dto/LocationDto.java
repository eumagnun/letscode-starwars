package br.com.danielamaral.starswarssocialnetwork.dto;

import br.com.danielamaral.starswarssocialnetwork.model.Location;
import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title  = "Location")
public class LocationDto {
    private long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double latitude;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double longitude;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String galaxy;

    public static LocationDto parseLocationDto(Location location){
        return new LocationDto(location.getId(),location.getLatitude(), location.getLongitude(), location.getGalaxy());
    }
}
