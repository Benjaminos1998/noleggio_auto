package noleggioAuto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import noleggioAuto.entities.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {

	@Query
	List<Auto> findAutoByTarga(String targa);

}
