package noleggioAuto.security.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import noleggioAuto.exception.EmailUtenteException;
import noleggioAuto.exception.EtaUtenteException;
import noleggioAuto.exception.PasswordNonValidaException;
import noleggioAuto.exception.PatenteUtenteException;
import noleggioAuto.exception.UtenteException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService service;

	@PostMapping("/registrazione")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		try {
			return ResponseEntity.ok(service.register(request));
		}catch(EmailUtenteException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante la registrazione. Email già in uso");
		}catch(PatenteUtenteException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante la registrazione. Patente già in uso");
		}catch(PasswordNonValidaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante la registrazione. Password troppo corta.");
		}catch(EtaUtenteException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante la registrazione. L'età minima per registrarsi è di 18 anni.");
		}catch(UtenteException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante la registrazione");
		}
		
		
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}

}
