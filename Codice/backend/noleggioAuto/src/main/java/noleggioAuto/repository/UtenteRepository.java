package noleggioAuto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import noleggioAuto.entities.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

	@Query
	Optional<Utente> findByNumeroPatente(String numeroPatente);

	@Query
	Optional<Utente> findByEmail(String email);

}
