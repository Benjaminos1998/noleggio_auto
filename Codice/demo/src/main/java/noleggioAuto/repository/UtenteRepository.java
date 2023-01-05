package noleggioAuto.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import noleggioAuto.entities.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer>{

	Optional<Utente> findByNumeroPatente(Integer numeroPatente);

	
}
