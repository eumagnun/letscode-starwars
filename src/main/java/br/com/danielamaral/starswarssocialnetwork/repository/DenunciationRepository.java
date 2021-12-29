package br.com.danielamaral.starswarssocialnetwork.repository;

import br.com.danielamaral.starswarssocialnetwork.model.Denunciation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DenunciationRepository extends JpaRepository<Denunciation, Long> {

    Long countDenunciationBySuspectID(long suspectId);
}
