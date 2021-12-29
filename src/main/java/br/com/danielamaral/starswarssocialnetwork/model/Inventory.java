package br.com.danielamaral.starswarssocialnetwork.model;

import br.com.danielamaral.starswarssocialnetwork.dto.InventoryDto;
import br.com.danielamaral.starswarssocialnetwork.dto.ItemInventoryDto;
import br.com.danielamaral.starswarssocialnetwork.dto.RebelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemInventory> inventoryItems;


    public static Inventory parseInventory(InventoryDto inventoryDto) {
        List<ItemInventory> list = new ArrayList<>();
        inventoryDto.getInventoryItemsDto()
                        .forEach(itemInventoryDto -> list
                        .add(ItemInventory.parseItemInventory(itemInventoryDto)));
        return new Inventory(inventoryDto.getId(),list);
    }
}
