package org.Team3.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RawIngredientTest {

    private RawIngredient rawIngredient;

    @BeforeEach
    void setUp() {
        rawIngredient = new RawIngredient();
    }

    @Test
    void testRawIngredientProperties() {
        rawIngredient.setId(1L);
        assertEquals(1L, rawIngredient.getId());

        rawIngredient.setName("Test Ingredient");
        assertEquals("Test Ingredient", rawIngredient.getName());

        rawIngredient.setQuantity(10);
        assertEquals(10, rawIngredient.getQuantity());
    }
}