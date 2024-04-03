package org.Team3.Controllers;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
public class AlertControllerTest {
    AlertController alertController = new AlertController();

    @Test
    public void testShowPage() {
        String result = alertController.showPage();
        Assert.assertEquals(result, "alerts");
    }
}

