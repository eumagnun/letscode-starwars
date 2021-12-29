package br.com.danielamaral.starswarssocialnetwork.dto;

import br.com.danielamaral.starswarssocialnetwork.model.Item;
import br.com.danielamaral.starswarssocialnetwork.model.ItemInventory;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
