package noleggioAuto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.Noleggio;
import noleggioAuto.repository.NoleggioRepository;

@Service
public class NoleggioService {

	private final NoleggioRepository noleggioRepository;

	@Autowired
	public NoleggioService(NoleggioRepository noleggioRepository) {
		this.noleggioRepository = noleggioRepository;
	}
	
	public List<Noleggio> getNoleggi(){
		return noleggioRepository.findAll();
		
	}

	public void addNoleggio(Noleggio noleggio) throws IllegalAccessException {
		Optional<Noleggio> noleggioById = noleggioRepository.findById(noleggio.getIdNoleggio());
		if (noleggioById.isPresent()) {
			throw new IllegalAccessException("Noleggio con id " + noleggio.getIdNoleggio() + " è già presente");
		}
		noleggioRepository.save(noleggio);
	}
}
