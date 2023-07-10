package noleggioAuto.security.auth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUnauthenticatedAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/auth"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testAuthenticatedAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/auth"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}



