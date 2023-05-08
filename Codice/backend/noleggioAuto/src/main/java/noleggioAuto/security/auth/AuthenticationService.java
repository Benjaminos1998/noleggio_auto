package noleggioAuto.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import noleggioAuto.security.config.JwtService;
import noleggioAuto.entities.Ruolo;
import noleggioAuto.entities.Utente;
import noleggioAuto.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepository userRepository;
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
				.build();
		this.userRepository.save(user);
		
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
		var user = this.userRepository.findByEmail(request.getEmail())
				.orElseThrow();
		
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
		
		
	}

}
