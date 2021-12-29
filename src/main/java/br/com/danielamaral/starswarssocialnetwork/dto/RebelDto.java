package br.com.danielamaral.starswarssocialnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.danielamaral.starswarssocialnetwork.model.Gender;
import br.com.danielamaral.starswarssocialnetwork.model.Rebel;
import br.com.danielamaral.starswarssocialnetwork.model.RebelStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RebelDto {
    private long id;
    private String name;
    private long age;
    private Gender gender;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RebelStatus status = RebelStatus.OK;
    private LocationDto locationDto;
    private InventoryDto inventoryDto;

    public static RebelDto parseDto(Rebel rebel) {
        return new RebelDto(rebel.getId(),
                rebel.getName(),
                rebel.getAge(),
                rebel.getGender(),
                rebel.getStatus(),
                LocationDto.parseLocationDto(rebel.getLocation()),
                InventoryDto.parseInventoryDto(rebel.getInventory())
        );
    }
}
