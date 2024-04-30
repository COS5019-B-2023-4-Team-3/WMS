package org.Team3.Controllers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UnderConstructionControllerTest {

    private UnderConstructionController underConstructionController;

    @BeforeEach
    public void setup() {
        underConstructionController = new UnderConstructionController();
    }

    @DisplayName("Show Under Construction Page Returns Correct View")
    @Test
    public void showUnderConstructionPageReturnsCorrectView() {
        String viewName = underConstructionController.showUnderConstructionPage();
        assertEquals("under-construction", viewName);
    }
}