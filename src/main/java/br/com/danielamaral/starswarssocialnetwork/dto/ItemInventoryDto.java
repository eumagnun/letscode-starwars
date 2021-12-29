package br.com.danielamaral.starswarssocialnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.danielamaral.starswarssocialnetwork.model.ItemInventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemInventoryDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private ItemDto itemDto;
    private long quantity;

    public static ItemInventoryDto parseItemInventoryDto(ItemInventory itemInventory){
        return new ItemInventoryDto(itemInventory.getId(), ItemDto.parseItemDto(itemInventory.getItem()),itemInventory.getQuantity());
    }
}
