package noleggioAuto.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class JwtServiceTest {

    private static final String SECRET_KEY = "secretkey";

    private JwtService jwtService;

    @BeforeEach
    public void setup() {
        jwtService = new JwtService();
    }

    @Test
    public void testGenerateToken() {
        String username = "testuser";
        UserDetails userDetails = User.withUsername(username)
                .password("password")
                .roles("USER")
                .build();

        String token = jwtService.generateToken(userDetails);

        Assertions.assertNotNull(token);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
        String parsedUsername = claims.getSubject();
        Assertions.assertEquals(username, parsedUsername);
    }

    @Test
    public void testValidateToken() {
        String username = "testuser";
        UserDetails userDetails = User.withUsername(username)
                .password("password")
                .roles("USER")
                .build();

        String token = jwtService.generateToken(userDetails);

        Assertions.assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    public void testExpiredToken() {
        String username = "testuser";
        UserDetails userDetails = User.withUsername(username)
                .password("password")
                .roles("USER")
                .build();

        Date expirationDate = new Date(System.currentTimeMillis() - 1000);
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();

       Assertions.assertFalse(jwtService.isTokenValid(token, userDetails));
    }
}

