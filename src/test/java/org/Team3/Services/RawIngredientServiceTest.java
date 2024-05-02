package org.Team3.Services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.Team3.Entities.RawIngredient;
import org.Team3.Repositories.RawIngredientRepository;
import org.Team3.Services.RawIngredientService;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RawIngredientServiceTest {

    @Mock
    private RawIngredientRepository rawIngredientRepository;

    @InjectMocks
    private RawIngredientService rawIngredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetAllRawIngredients() {
        RawIngredient ingredient = new RawIngredient();
        when(rawIngredientRepository.findAll()).thenReturn(Arrays.asList(ingredient));

        List<RawIngredient> ingredients = rawIngredientService.getAllRawIngredients();

        assertEquals(1, ingredients.size());
        assertEquals(ingredient, ingredients.get(0));
    }

    @Test
    void shouldGetRawIngredientById() {
        RawIngredient ingredient = new RawIngredient();
        when(rawIngredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient));

        RawIngredient foundIngredient = rawIngredientService.getRawIngredientById(1L);

        assertEquals(ingredient, foundIngredient);
    }

    @Test
    void shouldReturnNullWhenIngredientNotFoundById() {
        when(rawIngredientRepository.findById(anyLong())).thenReturn(Optional.empty());

        RawIngredient foundIngredient = rawIngredientService.getRawIngredientById(1L);

        assertNull(foundIngredient);
    }

    @Test
    void shouldCreateRawIngredient() {
        RawIngredient ingredient = new RawIngredient();
        when(rawIngredientRepository.save(any(RawIngredient.class))).thenReturn(ingredient);

        RawIngredient createdIngredient = rawIngredientService.createRawIngredient(ingredient);

        assertEquals(ingredient, createdIngredient);
    }

    @Test
    void shouldDeleteRawIngredient() {
        when(rawIngredientRepository.existsById(anyLong())).thenReturn(true);

        boolean isDeleted = rawIngredientService.deleteRawIngredient(1L);

        assertTrue(isDeleted);
        verify(rawIngredientRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void shouldNotDeleteRawIngredientWhenNotFound() {
        when(rawIngredientRepository.existsById(anyLong())).thenReturn(false);

        boolean isDeleted = rawIngredientService.deleteRawIngredient(1L);

        assertFalse(isDeleted);
        verify(rawIngredientRepository, times(0)).deleteById(anyLong());
    }

    @Test
    void shouldUpdateRawIngredient() {
        RawIngredient ingredient = new RawIngredient();
        ingredient.setId(1L);
        when(rawIngredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient));
        when(rawIngredientRepository.save(any(RawIngredient.class))).thenReturn(ingredient);

        RawIngredient updatedIngredient = rawIngredientService.updateRawIngredient(ingredient);

        assertNotNull(updatedIngredient, "Updated ingredient should not be null");
        assertEquals(ingredient, updatedIngredient, "Updated ingredient should match the original ingredient");
    }

    @Test
    void shouldReturnNullWhenUpdatingNonExistingIngredient() {
        RawIngredient ingredient = new RawIngredient();
        when(rawIngredientRepository.findById(anyLong())).thenReturn(Optional.empty());

        RawIngredient updatedIngredient = rawIngredientService.updateRawIngredient(ingredient);

        assertNull(updatedIngredient);
    }

    @Test
    void shouldGetAllIngredientsByName() {
        RawIngredient ingredient = new RawIngredient();
        when(rawIngredientRepository.findAll(any(Sort.class))).thenReturn(List.of(ingredient));

        List<RawIngredient> ingredients = rawIngredientService.getAllIngredientsByName();

        assertEquals(1, ingredients.size());
        assertEquals(ingredient, ingredients.get(0));
    }

    @Test
    void shouldGetAllIngredientsByQuantity() {
        RawIngredient ingredient = new RawIngredient();
        when(rawIngredientRepository.findAll(any(Sort.class))).thenReturn(List.of(ingredient));

        List<RawIngredient> ingredients = rawIngredientService.getAllIngredientsByQuantity();

        assertEquals(1, ingredients.size());
        assertEquals(ingredient, ingredients.get(0));
    }
}