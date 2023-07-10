package noleggioAuto.security.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthenticationResponseTest {

	@Test
	public void test() {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		authenticationResponse.setToken("123");

		assertEquals("123", authenticationResponse.getToken());
		
		AuthenticationResponse authenticationResponse1 = new AuthenticationResponse("123");	
		AuthenticationResponse authenticationResponse2 = new AuthenticationResponse("456");	
		assertEquals(authenticationResponse.toString(), authenticationResponse1.toString());
		assertEquals(authenticationResponse.hashCode(), authenticationResponse1.hashCode());
		assertEquals(true, authenticationResponse.equals(authenticationResponse1));
		assertEquals(false, authenticationResponse.equals(authenticationResponse2));
		
	}

	@Test
	public void test2() {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse("123");
		assertEquals("123", authenticationResponse.getToken());

	}

	@Test
	public void test3() {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();

		authenticationResponse = AuthenticationResponse.builder().token("123").build();

		assertEquals("123", authenticationResponse.getToken());

	}

}
