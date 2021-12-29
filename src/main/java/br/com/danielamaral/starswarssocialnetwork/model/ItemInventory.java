package br.com.danielamaral.starswarssocialnetwork.model;

import br.com.danielamaral.starswarssocialnetwork.dto.ItemInventoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Item item;
    private long quantity;

    public static ItemInventory parseItemInventory(ItemInventoryDto itemInventoryDto){
        return new ItemInventory(itemInventoryDto.getId(), Item.parseItem(itemInventoryDto.getItemDto()),itemInventoryDto.getQuantity());
    }
}
