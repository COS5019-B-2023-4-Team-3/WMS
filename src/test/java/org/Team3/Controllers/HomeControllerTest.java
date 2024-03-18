package org.Team3.Controllers;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    public void testShowHomepageWithAdminRole() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/homepage"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("homepage"))
//                .andExpect(MockMvcResultMatchers.model().attribute("role", "ADMIN"));
//    }
//
//    @Test
//    @WithMockUser(roles = "EMPLOYEE")
//    public void testShowHomepageWithEmployeeRole() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/homepage"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("homepage"))
//                .andExpect(MockMvcResultMatchers.model().attribute("role", "EMPLOYEE"));
//    }
//
//    @Test
//    @WithMockUser(roles = "EXTERNAL")
//    public void testShowHomepageWithExternalRole() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/homepage"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("homepage"))
//                .andExpect(MockMvcResultMatchers.model().attribute("role", "EXTERNAL"));
//    }
//
//    @Test
//    public void testHandleUnauthorizedPostRequest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/homepage"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(result -> assertTrue(Objects.requireNonNull(
//                        result.getResponse().getRedirectedUrl()).matches(".*/login.*")));
//    }
//
//    @Test
//    public void testLogout() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?logout"));
//    }
}
