package br.com.danielamaral.starswarssocialnetwork.model;

import br.com.danielamaral.starswarssocialnetwork.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.List;

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
