package org.Team3.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RawIngredientTest {

    private RawIngredient rawIngredient;

    @BeforeEach
    void setUp() {
        rawIngredient = new RawIngredient();
    }

    @Test
    void getId() {
        rawIngredient.setId(1L);
        assertEquals(1L, rawIngredient.getId());
    }

    @Test
    void getName() {
        rawIngredient.setName("Test Ingredient");
        assertEquals("Test Ingredient", rawIngredient.getName());
    }

    @Test
    void getQuantity() {
        rawIngredient.setQuantity(10);
        assertEquals(10, rawIngredient.getQuantity());
    }
}