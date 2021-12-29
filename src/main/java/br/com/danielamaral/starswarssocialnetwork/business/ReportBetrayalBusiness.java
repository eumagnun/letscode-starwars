package br.com.danielamaral.starswarssocialnetwork.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.danielamaral.starswarssocialnetwork.exception.MultipleDenunciationSameSuspectException;
import br.com.danielamaral.starswarssocialnetwork.model.Denunciation;
import br.com.danielamaral.starswarssocialnetwork.model.Rebel;
import br.com.danielamaral.starswarssocialnetwork.model.RebelStatus;
import br.com.danielamaral.starswarssocialnetwork.repository.DenunciationRepository;
import br.com.danielamaral.starswarssocialnetwork.repository.RebelRepository;

@Service
public class ReportBetrayalBusiness {

    public static final int DENUNCIATION_LIMIT = 3;

    @Autowired
    private DenunciationRepository denunciationRepository;
    @Autowired
    private RebelRepository rebelRepository;

    public void denounce(Denunciation denunciation) throws MultipleDenunciationSameSuspectException {
        try {
            denunciationRepository.save(denunciation);
            changeStatusRebel(denunciation);
        }catch (DataIntegrityViolationException ex){
            throw  new MultipleDenunciationSameSuspectException("Só é possivel denúnciar um suspeito uma única vez");
        }
    }

    private void changeStatusRebel(final Denunciation denunciation) {
        long suspectId = denunciation.getSuspectID();
        final Long count = denunciationRepository.countDenunciationBySuspectID(suspectId);

        if(count >=DENUNCIATION_LIMIT){
            final Optional<Rebel> optional = rebelRepository.findById(suspectId);
            if(optional.isPresent()) {
                final Rebel rebel = optional.get();
                rebel.setStatus(RebelStatus.TRAITOR);
                rebelRepository.save(rebel);
            }
        }
    }
}
