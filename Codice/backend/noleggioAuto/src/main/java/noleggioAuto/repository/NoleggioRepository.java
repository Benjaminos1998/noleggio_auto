package noleggioAuto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import noleggioAuto.entities.Noleggio;

@Repository
public interface NoleggioRepository extends JpaRepository<Noleggio, Long> {

	@Query
	Optional<Noleggio> findNoleggioByAuto(Long idAuto);

	@Query
	Optional<Noleggio> findNoleggioByUtenteRegistrato(Long utenteRegistratoId);
}
