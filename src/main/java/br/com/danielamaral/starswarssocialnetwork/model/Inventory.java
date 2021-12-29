package br.com.danielamaral.starswarssocialnetwork.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.danielamaral.starswarssocialnetwork.dto.InventoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
