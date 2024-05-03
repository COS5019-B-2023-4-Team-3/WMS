package org.Team3.Controllers;

import org.Team3.Entities.Role;
import org.Team3.Entities.User;
import org.Team3.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username="test_admin", roles={"ADMIN"})
    void shouldShowPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="test_employee", roles={"EMPLOYEE"})
    void shouldNotShowPageEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username="test_vendor", roles={"EXTERNAL"})
    void shouldNotShowPageExternal() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }


    @WithMockUser(username="test_admin", roles={"ADMIN"}, value = "1")
    void shouldDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username="test_admin", roles={"ADMIN"})
    void shouldShowEditUserForm() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/users/edit/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="test_employee", roles={"EMPLOYEE"})
    void shouldNotShowEditPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/edit/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username="test_vendor", roles={"EXTERNAL"})
    void shouldNotShowEditPageExternal() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/edit/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}