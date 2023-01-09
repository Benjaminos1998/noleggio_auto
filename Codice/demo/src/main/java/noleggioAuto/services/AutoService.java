package noleggioAuto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.Auto;
import noleggioAuto.repository.AutoRepository;

@Service
public class AutoService {

	private final AutoRepository autoRepository;
	
	@Autowired
	public AutoService(AutoRepository autoRepository) {
		this.autoRepository = autoRepository;
	}
	
	public List<Auto> getAuto(){
		return autoRepository.findAll();
	}
	
	public void addNewAuto(Auto auto) throws IllegalAccessException {
		Optional<Auto> autoByTarga = autoRepository.findAutoByTarga(auto.getTarga());
		if(autoByTarga.isPresent()){
			throw new IllegalAccessException("Auto già inserita");
		}
		autoRepository.save(auto);
	}
	
	public void deleteAuto(String targa) {
		boolean exist = autoRepository.existsById(targa);
		if(!exist) {
			throw new IllegalStateException("L'auto con targa "+targa+" non esiste");	
		}
		autoRepository.deleteById(targa);
	}
}
