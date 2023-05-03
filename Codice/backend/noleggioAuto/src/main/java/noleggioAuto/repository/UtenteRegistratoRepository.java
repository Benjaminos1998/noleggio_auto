package noleggioAuto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import noleggioAuto.entities.UtenteRegistrato;

@Repository
public interface UtenteRegistratoRepository extends JpaRepository<UtenteRegistrato, Long> {

	@Query
	Optional<UtenteRegistrato> findByNumeroPatente(String numeroPatente);
	@Query
	Optional<UtenteRegistrato> findByUsername(String username);
	
}
