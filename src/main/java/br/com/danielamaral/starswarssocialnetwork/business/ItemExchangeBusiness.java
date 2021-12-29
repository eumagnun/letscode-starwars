package br.com.danielamaral.starswarssocialnetwork.business;

import br.com.danielamaral.starswarssocialnetwork.exception.IncompatiblePontuationException;
import br.com.danielamaral.starswarssocialnetwork.exception.InventoryBlockedException;
import br.com.danielamaral.starswarssocialnetwork.exception.ParticipantNotFoundException;
import br.com.danielamaral.starswarssocialnetwork.model.ItemInventory;
import br.com.danielamaral.starswarssocialnetwork.model.Negotiation;
import br.com.danielamaral.starswarssocialnetwork.model.Rebel;
import br.com.danielamaral.starswarssocialnetwork.model.RebelStatus;
import br.com.danielamaral.starswarssocialnetwork.repository.RebelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemExchangeBusiness {

    @Autowired
    private RebelRepository rebelRepository;

    public void exchange(final Negotiation negotiation) throws IncompatiblePontuationException, InventoryBlockedException, ParticipantNotFoundException {
        final Optional<Rebel> optionalRebel1 = rebelRepository.findById(negotiation.getNegotiationPart1().getIdPart());
        final Optional<Rebel> optionalRebel2 = rebelRepository.findById(negotiation.getNegotiationPart2().getIdPart());

        if (optionalRebel1.isEmpty() || optionalRebel2.isEmpty()){
            throw  new ParticipantNotFoundException("Um dos participantes não foi encontrado");
        }

        final Rebel rebel1 = optionalRebel1.get();
        final Rebel rebel2 = optionalRebel2.get();

        validateTotalPoints(negotiation.getNegotiationPart1().getItensParticipant()
                , negotiation.getNegotiationPart2().getItensParticipant());

        validateInventoryParticipants(rebel1, rebel2);

        doChangesParticipantInventory(rebel1,
                negotiation.getNegotiationPart2().getItensParticipant(),
                negotiation.getNegotiationPart1().getItensParticipant());

        doChangesParticipantInventory(rebel2,
                negotiation.getNegotiationPart1().getItensParticipant(),
                negotiation.getNegotiationPart2().getItensParticipant());

        rebelRepository.save(rebel1);
        rebelRepository.save(rebel2);

    }


    private void validateInventoryParticipants(Rebel rebel1, Rebel rebel2) throws InventoryBlockedException {
        if (rebel1.getStatus().equals(RebelStatus.TRAITOR) || rebel2.getStatus().equals(RebelStatus.TRAITOR)) {
            throw new InventoryBlockedException("Um dos participantes está com o inventário bloqueado");
        }
    }


    private void doChangesParticipantInventory(final Rebel rebel, final List<ItemInventory> offeredItemsRebel2, final List<ItemInventory> offeredItemsRebel1) {
        for (final ItemInventory iRebel1 : rebel.getInventory().getInventoryItems()) {

            ////add the quantity received
            for (final ItemInventory offeredItemRebel2 : offeredItemsRebel2) {
                if (iRebel1.getItem().equals(offeredItemRebel2.getItem())) {
                    iRebel1.setQuantity(iRebel1.getQuantity() + offeredItemRebel2.getQuantity());
                }
            }
            //subtract the quantity offered
            for (final ItemInventory offeredItemRebel1 : offeredItemsRebel1) {
                if (iRebel1.getItem().equals(offeredItemRebel1.getItem())) {
                    iRebel1.setQuantity(iRebel1.getQuantity() - offeredItemRebel1.getQuantity());
                }
            }
        }
    }

    private void validateTotalPoints(List<ItemInventory> offeredItemsRebel1, List<ItemInventory> offeredItemsRebel2) throws IncompatiblePontuationException {
    	final long totalPointsPart1 = offeredItemsRebel1.stream().mapToLong(i->i.getItem().getPoints()*i.getQuantity()).sum();

        final long totalPointsPart2 = offeredItemsRebel2.stream().mapToLong(i->i.getItem().getPoints()*i.getQuantity()).sum();

        if (totalPointsPart1 != totalPointsPart2) {
            throw new IncompatiblePontuationException("A quantidade de pontos é incompatível");
        }
    }

}
