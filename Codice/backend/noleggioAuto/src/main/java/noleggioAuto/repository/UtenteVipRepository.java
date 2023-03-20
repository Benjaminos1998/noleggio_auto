package noleggioAuto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import noleggioAuto.entities.UtenteVip;

@Repository
public interface UtenteVipRepository extends JpaRepository<UtenteVip, Integer> {

	Optional<UtenteVip> findByIdCarta(Integer idCarta);
	Optional<UtenteVip> findByNumeroPatente(Integer numeroPatente);
	
}
