package br.com.danielamaral.starswarssocialnetwork.business;

import br.com.danielamaral.starswarssocialnetwork.exception.MultipleDenunciationSameSuspectException;
import br.com.danielamaral.starswarssocialnetwork.model.Denunciation;
import br.com.danielamaral.starswarssocialnetwork.model.Rebel;
import br.com.danielamaral.starswarssocialnetwork.model.RebelStatus;
import br.com.danielamaral.starswarssocialnetwork.repository.DenunciationRepository;
import br.com.danielamaral.starswarssocialnetwork.repository.RebelRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    private void changeStatusRebel(Denunciation denunciation) {
        long suspectId = denunciation.getSuspectID();
        Long count = denunciationRepository.countDenunciationBySuspectID(suspectId);

        if(count >=DENUNCIATION_LIMIT){
            Optional<Rebel> optional = rebelRepository.findById(suspectId);
            if(optional.isPresent()) {
                Rebel rebel = optional.get();
                rebel.setStatus(RebelStatus.TRAITOR);
                rebelRepository.save(rebel);
            }
        }
    }
}
