package org.Team3.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.Team3.Entities.RawIngredient;
import org.Team3.Services.RawIngredientService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class RawIngredientControllerTest {

    @Mock
    private RawIngredientService rawIngredientService;

    @Mock
    private Model model;

    @InjectMocks
    private RawIngredientController rawIngredientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldShowRawIngredientPage() {
        RawIngredient ingredient = new RawIngredient();
        when(rawIngredientService.getAllRawIngredients()).thenReturn(Arrays.asList(ingredient));

        String viewName = rawIngredientController.showRawIngredientPage(model);

        verify(model, times(1)).addAttribute("rawIngredients", Arrays.asList(ingredient));
        assertEquals("/raw-ingredients", viewName);
    }

    @Test
    void shouldCreateRawIngredient() {
        RawIngredient ingredient = new RawIngredient();
        when(rawIngredientService.createRawIngredient(any(RawIngredient.class))).thenReturn(ingredient);

        String viewName = rawIngredientController.createRawIngredient(ingredient, model);

        assertEquals("redirect:/raw-ingredients", viewName);
    }

    @Test
    void shouldNotCreateRawIngredientWhenServiceThrowsException() {
        RawIngredient ingredient = new RawIngredient();
        when(rawIngredientService.createRawIngredient(any(RawIngredient.class))).thenThrow(new RuntimeException());

        String viewName = rawIngredientController.createRawIngredient(ingredient, model);

        verify(model, times(1)).addAttribute("error", "Failed to create ingredient");
        assertEquals("redirect:/raw-ingredients/create?error=failed_to_create_product", viewName);
    }

    @Test
    void shouldUpdateRawIngredient() {
        RawIngredient ingredient = new RawIngredient();
        when(rawIngredientService.getRawIngredientById(anyLong())).thenReturn(ingredient);
        when(rawIngredientService.updateRawIngredient(any(RawIngredient.class))).thenReturn(ingredient);

        String viewName = rawIngredientController.updateRawIngredient(1L, ingredient, model);

        assertEquals("redirect:/raw-ingredients", viewName);
    }

    @Test
    void shouldDeleteRawIngredient() {
        when(rawIngredientService.deleteRawIngredient(anyLong())).thenReturn(true);

        String viewName = rawIngredientController.deleteRawIngredient(1L);

        assertEquals("redirect:/raw-ingredients", viewName);
    }

}