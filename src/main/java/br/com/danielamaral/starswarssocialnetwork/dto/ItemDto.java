package br.com.danielamaral.starswarssocialnetwork.dto;

import br.com.danielamaral.starswarssocialnetwork.model.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long points;

    public static ItemDto parseItemDto(Item item){
        return new ItemDto(item.getId(), item.getName(), item.getPoints());
    }

}
