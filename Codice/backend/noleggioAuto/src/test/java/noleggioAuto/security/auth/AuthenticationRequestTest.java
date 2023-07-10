package noleggioAuto.security.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthenticationRequestTest {

	@Test
	public void test() {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setEmail("rossi@gmaill.com");
		authenticationRequest.setPassword("password");
		
		assertEquals("rossi@gmaill.com", authenticationRequest.getEmail());
		assertEquals("password", authenticationRequest.getPassword());
	}
	
	@Test
	public void test2() {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("rossi@gmaill.com", "password");
		
		assertEquals("rossi@gmaill.com", authenticationRequest.getEmail());
		assertEquals("password", authenticationRequest.getPassword());
		
	}
	
	@Test
	public void test3() {
		AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
				.email("rossi@gmaill.com")
				.password("password")
				.build();
		
		assertEquals("rossi@gmaill.com", authenticationRequest.getEmail());
		assertEquals("password", authenticationRequest.getPassword());
		
		
		AuthenticationRequest authenticationRequest1 = new AuthenticationRequest("rossi@gmaill.com", "password");
		AuthenticationRequest authenticationRequest2 = new AuthenticationRequest("verdi@gmaill.com", "password1");
		
		assertEquals(authenticationRequest.toString(), authenticationRequest1.toString());
		assertEquals(authenticationRequest.hashCode(), authenticationRequest1.hashCode());
		assertEquals(true,authenticationRequest.equals(authenticationRequest1));
		assertEquals(false,authenticationRequest.equals(authenticationRequest2));
		
	}
}
