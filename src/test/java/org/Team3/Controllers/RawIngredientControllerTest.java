package org.Team3.Controllers;

import org.Team3.Entities.RawIngredient;
import org.Team3.Entities.User;
import org.Team3.Services.RawIngredientService;
import org.Team3.Services.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@SpringBootTest
public class RawIngredientControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    @MockBean
    private RawIngredientService rawIngredientService;

    @InjectMocks
    private RawIngredientController rawIngredientController;

    @BeforeClass
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testShowPage() throws Exception {
        mockMvc.perform(get("/raw-ingredients")).andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    void testCreateRawIngredient_Success() throws Exception {
        RawIngredient rawIngredient = new RawIngredient();
        rawIngredient.setName("Ingredient Test");
        rawIngredient.setDescription("Description Test");
        rawIngredient.setQuantity(123);

        mockMvc.perform(MockMvcRequestBuilders.post("/raw-ingredients-create")
                        .flashAttr("rawIngredient", rawIngredient))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/raw-ingredients"));
    }
    @Test
    void testCreateRawIngredient_Failure() throws Exception {
        RawIngredient rawIngredient = new RawIngredient();
        rawIngredient = new RawIngredient();
        rawIngredient.setName("Ingredient Test");
        rawIngredient.setDescription("Description Test");
        rawIngredient.setQuantity(123);

        // Mock the service to throw an exception
        Mockito.doThrow(new Exception("Failed to create ingredient")).when(rawIngredientService).createRawMaterial(rawIngredient);

        mockMvc.perform(MockMvcRequestBuilders.post("/raw-ingredients-create")
                        .flashAttr("rawIngredient", rawIngredient))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/raw-ingredients/create?error=failed_to_create_product"));

    }
    @Test
    void testRawIngredientsCreate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/raw-ingredients/create"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/raw-ingredients-create"));
    }
//    @Test
//    void showRawIngredientPage() {
//    }
//    @Test
//    void rawIngredientsCreate() {
//    }
//
//    @Test
//    void updateRawMaterialForm() {
//    }
//
//    @Test
//    void deleteRawIngredient() {
//    }
//
//    @Test
//    void updateRawIngredient() {
//    }
//
//    @Test
//    void showIngredientsPageSorted() {
//    }
//
//    @Test
//    void showIngredientsPageSortedByQuantity() {
//    }
}