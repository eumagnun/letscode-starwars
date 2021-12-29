package br.com.danielamaral.starswarssocialnetwork.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.danielamaral.starswarssocialnetwork.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private long points;

    public static Item parseItem(ItemDto itemDto){
        return new Item(itemDto.getId(), itemDto.getName(), itemDto.getPoints());
    }
}
