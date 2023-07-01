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
	Optional<Auto> findAutoByTarga(String targa);
	@Query
	List<Auto> findAutoByTargaList(String targa);

}
