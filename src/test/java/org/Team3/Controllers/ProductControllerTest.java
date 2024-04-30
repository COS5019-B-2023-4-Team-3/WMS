package org.Team3.Controllers;

import org.Team3.Entities.User;
import org.Team3.Services.UserService;
import org.Team3.Entities.Product;
import org.Team3.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ProductService productService;

    private MockMvc mockMvc;

    @BeforeClass
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(username = "test_admin", roles = {"ADMIN"})
    void shouldShowProductsPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test_vendor", roles = {"EXTERNAL"})
    void shouldShowProductsVendorPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test_employee", roles = {"EMPLOYEE"})
    void shouldShowProductsEmployeePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testShowCreateProductForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/create"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("products-create"));
    }

    //edit form test fails
    @Test
    public void testShowEditProductForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/edit/{id}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("products-edit{id}"));
    }
}