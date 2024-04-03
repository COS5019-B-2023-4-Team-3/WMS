package org.Team3.Controllers;

import org.Team3.Services.ProductService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductControllerTest {
    @Mock
    ProductService productService;
    @InjectMocks
    ProductController productController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowPage() {
        String result = productController.showPage();
        Assert.assertEquals(result, "products");
    }

}