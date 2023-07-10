package noleggioAuto.security.auth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import noleggioAuto.repository.UtenteRepository;
import noleggioAuto.security.config.JwtService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthenticationTest {

    @Mock
    private UtenteRepository utenteRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister_SuccessfulRegistration() {
        RegisterRequest request = new RegisterRequest("John", "Doe", "john.doe@example.com",
                "password", LocalDate.of(1990, 1, 1), "ABC123");

        when(utenteRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(utenteRepository.findByNumeroPatente(request.getNumeroPatente())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(jwtService.generateToken(any(Utente.class))).thenReturn("jwtToken");

        AuthenticationResponse response = authenticationService.register(request);

        verify(utenteRepository).save(any(Utente.class));
        assertEquals("jwtToken", response.getToken());
    }

    @Test
    public void testRegister_EmailAlreadyExists() {
        RegisterRequest request = new RegisterRequest("John", "Doe", "john.doe@example.com",
                "password", LocalDate.of(1990, 1, 1), "ABC123");

        when(utenteRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new Utente()));

        assertThrows(EmailUtenteException.class, () -> authenticationService.register(request));
    }

    @Test
    public void testRegister_DuplicatePatenteNumber() {
        RegisterRequest request = new RegisterRequest("John", "Doe", "john.doe@example.com",
                "password", LocalDate.of(1990, 1, 1), "ABC123");

        when(utenteRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(utenteRepository.findByNumeroPatente(request.getNumeroPatente())).thenReturn(Optional.of(new Utente()));

        assertThrows(PatenteUtenteException.class, () -> authenticationService.register(request));
    }

    // Add more test methods to cover other scenarios

    @Test
    public void testAuthenticate_SuccessfulAuthentication() {
        AuthenticationRequest request = new AuthenticationRequest("john.doe@example.com", "password");

        Utente user = new Utente();
        user.setEmail("john.doe@example.com");

        when(utenteRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any(Utente.class))).thenReturn("jwtToken");

        AuthenticationResponse response = authenticationService.authenticate(request);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        assertEquals("jwtToken", response.getToken());
    }

    @Test
    public void testAuthenticate_InvalidEmail() {
        AuthenticationRequest request = new AuthenticationRequest("invalidEmail@example.com", "password");

        when(utenteRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> authenticationService.authenticate(request));
    }

    // Add more test methods to cover other scenarios
}

