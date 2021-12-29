package br.com.danielamaral.starswarssocialnetwork.repository;

import br.com.danielamaral.starswarssocialnetwork.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
