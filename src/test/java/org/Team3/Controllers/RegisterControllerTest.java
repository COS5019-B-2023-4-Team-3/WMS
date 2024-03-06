package org.Team3.Controllers;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserService userService;
//
//    private final String email = "newuser@example.com";
//
//    @BeforeEach
//    public void setup(){
//        // Check if user exists and remove if found
//        if (userService.userExists(email)) {
//            User user = null;
//            for(User tmp: userService.getAllUsers()){
//                if(tmp.getUsername().equals(email)){
//                    user = tmp;
//                    break;
//                }
//            }
//            assert user != null;
//            userService.deleteUser(user.getId());
//        }
//    }
//
//    @Test
//    public void testShowSignupForm() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("register"));
//    }
//
//    @Test
//    public void testRegisterUser_Success() throws Exception {
//
//
//        // Register the new user
//        mockMvc.perform(MockMvcRequestBuilders.post("/register")
//                        .param("email", email)
//                        .param("password", "password"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
//    }
//
//    @Test
//    public void testRegisterUser_UserAlreadyExists() throws Exception {
//        // Assuming the user already exists in the system
//        mockMvc.perform(MockMvcRequestBuilders.post("/register")
//                        .param("email", "test_employee")
//                        .param("password", "test"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/register?error=username_already_exists"));
//    }
//
//    @AfterEach
//    public void tearDown(){
//        //delete the user again
//        if (userService.userExists(email)) {
//            User user = null;
//            for(User tmp: userService.getAllUsers()){
//                if(tmp.getUsername().equals(email)){
//                    user = tmp;
//                    break;
//                }
//            }
//            assert user != null;
//            userService.deleteUser(user.getId());
//        }
//    }
}