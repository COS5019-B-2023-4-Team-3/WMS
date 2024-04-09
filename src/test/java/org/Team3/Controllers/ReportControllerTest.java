package org.Team3.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test_employee", roles = {"EMPLOYEE"})
    public void testShowHomepageForAuthorizedEMPLOYEE() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reports"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("reports"));
    }

    @Test
    @WithMockUser(username = "test_admin", roles = {"ADMIN"})
    public void testShowHomepageForAuthorizedADMIN() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reports"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("reports"));
    }

    @Test
    @WithMockUser(username = "test_vendor", roles = {"EXTERNAL"})
    public void testShowHomepageForAuthorizedEXTERNAL() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reports"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("reports"));
    }
}