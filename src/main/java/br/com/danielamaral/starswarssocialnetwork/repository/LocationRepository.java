package br.com.danielamaral.starswarssocialnetwork.repository;

import br.com.danielamaral.starswarssocialnetwork.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
