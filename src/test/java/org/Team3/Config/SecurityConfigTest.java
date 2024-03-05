package org.Team3.Config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void accessUnsecuredEndpoint_thenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void accessSecuredEndpointWithNoAuthentication_thenRedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void accessSecuredEndpointWithAuthentication_thenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void loginWithValidCredentials_thenAuthenticated() throws Exception {
        mockMvc.perform(formLogin().user("test_employee").password("test"))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    public void loginWithInvalidCredentials_thenUnauthenticated() throws Exception {
        mockMvc.perform(formLogin().user("invalidUser").password("invalidPassword"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

    @Test
    public void logout_thenRedirectToLoginPage() throws Exception {
        mockMvc.perform(SecurityMockMvcRequestBuilders.logout())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?logout"));
    }
}