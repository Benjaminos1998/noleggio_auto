package noleggioAuto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import noleggioAuto.entities.Noleggio;

@Repository
public interface NoleggioRepository extends JpaRepository<Noleggio, Long> {

	List<Noleggio> findByAutoIdAuto(Long idAuto);
	List<Noleggio> findByUtenteRegistratoIdUtente(Long idUtente);
	Optional<Noleggio> findByIdNoleggio(Long idNoleggio);

}
