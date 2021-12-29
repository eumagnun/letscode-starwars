package br.com.danielamaral.starswarssocialnetwork.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.danielamaral.starswarssocialnetwork.model.Inventory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(title  = "Inventory")
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
