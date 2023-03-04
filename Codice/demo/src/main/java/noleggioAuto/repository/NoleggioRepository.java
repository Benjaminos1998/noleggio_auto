package noleggioAuto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import noleggioAuto.entities.Noleggio;

@Repository
public interface NoleggioRepository extends JpaRepository<Noleggio, Integer> {

	Optional<Noleggio> findById(Integer id);
	
}
