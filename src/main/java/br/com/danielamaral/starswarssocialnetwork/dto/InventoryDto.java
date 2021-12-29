package br.com.danielamaral.starswarssocialnetwork.dto;

import br.com.danielamaral.starswarssocialnetwork.model.Inventory;
import br.com.danielamaral.starswarssocialnetwork.model.Rebel;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private List<ItemInventoryDto> inventoryItemsDto;

    public static InventoryDto parseInventoryDto(Inventory inventory) {
        List<ItemInventoryDto> list = new ArrayList<>();
        inventory.getInventoryItems().forEach(itemInventory -> list.add(ItemInventoryDto.parseItemInventoryDto(itemInventory)));
        return new InventoryDto(inventory.getId(), list);
    }
}
