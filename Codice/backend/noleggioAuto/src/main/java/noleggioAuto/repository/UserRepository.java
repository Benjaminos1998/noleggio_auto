package noleggioAuto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import noleggioAuto.entities.Utente;

@Repository
public interface UserRepository extends JpaRepository<Utente, Long> {

	Optional<Utente> findByEmail(String email);

}
