package br.com.danielamaral.starswarssocialnetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegotiationPart {

    private long idPart;
    private List<ItemInventory> itensParticipant;
}
