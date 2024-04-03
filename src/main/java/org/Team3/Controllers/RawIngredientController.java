package org.Team3.Controllers;

import org.Team3.DTO.RawMaterialDto;
import org.Team3.Entities.RawIngredient;
import org.Team3.Services.RawIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * RawIngredientController class defines endpoints to handle CRUD (Create, Read, Update, Delete) operations related to raw ingredients.
 *
 * This controller is responsible for processing HTTP requests and returning appropriate responses for operations
 * on raw ingredients such as creating a new raw ingredient, updating an existing raw ingredient, and deleting a raw ingredient.
 */
@Controller
public class RawIngredientController {

    @Autowired
    private RawIngredientService rawIngredientService;

    /**
     * Displays the raw ingredients page.
     *
     * @return String representing the view name for the raw ingredients page.
     */
    @GetMapping("/raw-ingredients")
    public String showPage() {
        return "raw-ingredients";
    }

    /**
     * Creates a new raw ingredient in the database.
     *
     * @param rawMaterialDto RawMaterialDto object representing the raw material to create.
     * @return ResponseEntity containing a RawIngredient object representing the newly created raw ingredient.
     *         Returns HTTP status code CREATED (201) on success.
     */
    @PostMapping("/raw-ingredients-create")
    public ResponseEntity<RawIngredient> createRawMaterial(@RequestBody RawMaterialDto rawMaterialDto) {
        RawIngredient createdRawIngredient = rawIngredientService.createRawMaterial(rawMaterialDto);
        return new ResponseEntity<>(createdRawIngredient, HttpStatus.CREATED);
    }

    /**
     * Updates an existing raw ingredient in the database.
     *
     * @param id             Long representing the ID of the raw ingredient to update.
     * @param rawMaterialDto RawMaterialDto object representing the updated raw ingredient data.
     * @return ResponseEntity containing a RawIngredient object representing the updated raw ingredient.
     *         Returns HTTP status code OK (200) if the raw ingredient is updated successfully.
     *         Returns HTTP status code NOT_FOUND (404) if the raw ingredient with the given ID is not found.
     */
    @PutMapping("/raw-ingredients-update-{id}")
    public ResponseEntity<RawIngredient> updateRawMaterial(@PathVariable Long id, @RequestBody RawMaterialDto rawMaterialDto) {
        RawIngredient updatedRawIngredient = rawIngredientService.updateRawMaterial(id, rawMaterialDto);
        if (updatedRawIngredient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedRawIngredient, HttpStatus.OK);
    }

    /**
     * Deletes a raw ingredient from the database.
     *
     * @param id Long representing the ID of the raw ingredient to delete.
     * @return ResponseEntity with no content.
     *         Returns HTTP status code NO_CONTENT (204) if the raw ingredient is deleted successfully.
     *         Returns HTTP status code NOT_FOUND (404) if the raw ingredient with the given ID is not found.
     */
    @DeleteMapping("/raw-ingredients-delete-{id}")
    public ResponseEntity<Void> deleteRawMaterial(@PathVariable Long id) {
        boolean deleted = rawIngredientService.deleteRawMaterial(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}