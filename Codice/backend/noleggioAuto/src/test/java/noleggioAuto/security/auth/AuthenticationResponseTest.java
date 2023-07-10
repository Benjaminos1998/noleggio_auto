package noleggioAuto.security.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthenticationResponseTest {

	
	@Test
	public void test() {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		authenticationResponse.setToken("123");
		
		assertEquals("123", authenticationResponse.getToken());
	}
	@Test
	public void test2() {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse("123");		
		assertEquals("123", authenticationResponse.getToken());
	}
	
	@Test
	public void test3() {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		
		authenticationResponse = AuthenticationResponse.builder()
		.token("123")
		.build();
		
		
		assertEquals("123", authenticationResponse.getToken());
	}
	
	
}
