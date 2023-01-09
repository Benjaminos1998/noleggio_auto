package noleggioAuto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import noleggioAuto.entities.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, String> {

	@Query
	Optional<Auto> findAutoByTarga(String targa);

	
}
