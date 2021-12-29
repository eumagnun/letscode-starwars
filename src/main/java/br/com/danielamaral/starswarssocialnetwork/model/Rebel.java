package br.com.danielamaral.starswarssocialnetwork.model;

import br.com.danielamaral.starswarssocialnetwork.dto.RebelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rebel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private long age;
    private Gender gender;
    private RebelStatus status=RebelStatus.OK;

    @ManyToOne
    private Location location = new Location();

    @OneToOne(cascade = CascadeType.ALL)
    private Inventory inventory;

    public static Rebel parseRebel(RebelDto rebelDto) {
        return new Rebel(rebelDto.getId(),
                rebelDto.getName(),
                rebelDto.getAge(),
                rebelDto.getGender(),
                rebelDto.getStatus(),
                Location.parseLocation(rebelDto.getLocationDto()),
                Inventory.parseInventory(rebelDto.getInventoryDto())
        );
    }
}
