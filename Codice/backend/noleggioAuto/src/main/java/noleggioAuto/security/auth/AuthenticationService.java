package noleggioAuto.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import noleggioAuto.security.config.JwtService;
import noleggioAuto.entities.Ruolo;
import noleggioAuto.entities.Utente;
import noleggioAuto.exception.PatenteUtenteException;
import noleggioAuto.exception.EmailUtenteException;
import noleggioAuto.exception.EtaUtenteException;
import noleggioAuto.exception.PasswordNonValidaException;
import noleggioAuto.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UtenteRepository utenteRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	
	public AuthenticationResponse register(RegisterRequest request) {
		var user = Utente.builder()
				.nome(request.getNome())
				.cognome(request.getCognome())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.ruolo(Ruolo.UTENTE)
				.dataDiNascita(request.getDataDiNascita())
				.numeroPatente(request.getNumeroPatente())
				.numeroPunti(0)
				.noleggioInCorso(false)
				.eta(Utente.getEta(request.getDataDiNascita()))
				.build();
		if(this.utenteRepository.findByEmail(request.getEmail()).isPresent())
			throw new EmailUtenteException("Non è possibile che due utenti abbiano la stessa email");
		if(this.utenteRepository.findByNumeroPatente(request.getNumeroPatente()).isPresent())
			throw new PatenteUtenteException("Non è possibile che un utente abbia lo stesso numero di patente di un altro utente");
		if(request.getPassword().length() < 8)
			throw new PasswordNonValidaException("Non è possibile inserire una password con meno di 8 caratteri");
		if(user.getEta()<18)
			throw new EtaUtenteException("Non è possibile registrarsi se non si è maggiorenni");
		
		this.utenteRepository.save(user);
		
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
						)
				);
		var user = this.utenteRepository.findByEmail(request.getEmail())
				.orElseThrow();
		
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
		
		
	}

}
