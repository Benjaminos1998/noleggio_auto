package noleggioAuto.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utenti")
public class Utente implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Transient
	public final static int ETA = 18;
	@Transient
	public static final int LUNGHEZZA_MINIMA_PASSWORD = 8;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idUtente;
	@Column(name = "nome")
	public String nome;
	@Column(name = "cognome")
	public String cognome;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "numero_punti")
	private int numeroPunti;
	@Column(name = "data_di_nascita")
	private LocalDate dataDiNascita;
	@Column(name = "numero_patente")
	private String numeroPatente;
	@Column(name = "età")
	private int eta;
	@Enumerated(EnumType.STRING)
	private Ruolo ruolo;
	@Transient
	private boolean noleggioInCorso;

	public static int getEta(LocalDate dataDiNascita) {
		return Period.between(dataDiNascita, LocalDate.now()).getYears();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(ruolo.name()));
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Utente))
			return false;
		Utente utente = (Utente) o;
		return this.numeroPatente.equals(utente.numeroPatente);
	}

}