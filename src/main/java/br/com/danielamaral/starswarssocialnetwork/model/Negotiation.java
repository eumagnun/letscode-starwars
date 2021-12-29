package br.com.danielamaral.starswarssocialnetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Negotiation {
    private NegotiationPart negotiationPart1;
    private NegotiationPart negotiationPart2;
}
