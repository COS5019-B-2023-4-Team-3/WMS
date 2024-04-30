package org.Team3.Controllers;

import org.Team3.Entities.RawIngredient;
import org.Team3.Services.RawIngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RawIngredientController class defines endpoints to handle CRUD (Create, Read, Update, Delete) operations related to raw ingredients.*
 * This controller is responsible for processing HTTP requests and returning appropriate responses for operations
 * on raw ingredients such as creating a new raw ingredient, updating an existing raw ingredient, and deleting a raw ingredient.
 */
@Controller

public class RawIngredientController {


    private final RawIngredientService rawIngredientService;

    public RawIngredientController(RawIngredientService rawIngredientService){
        this.rawIngredientService = rawIngredientService;
    }


    /**
     * Creates a new raw ingredient in the database.
     *
     * @param rawIngredient RawMaterialDto object representing the raw material to create.
     * @return ResponseEntity containing a RawIngredient object representing the newly created raw ingredient.
     *         Returns HTTP status code CREATED (201) on success.
     */
    @PostMapping("/raw-ingredients-create")
    public String createRawIngredient(@ModelAttribute("rawIngredient") RawIngredient rawIngredient, Model model) {
        try {
            rawIngredientService.createRawMaterial(rawIngredient);
            return "redirect:/raw-ingredients";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create ingredient");
            return "redirect:/raw-ingredients/create?error=failed_to_create_product";
        }
    }

    /**
     * Updates an existing raw ingredient in the database.
     *
     * @param id             Long representing the ID of the raw ingredient to update.
     * @param rawIngredient RawMaterialDto object representing the updated raw ingredient data.
     * @return ResponseEntity containing a RawIngredient object representing the updated raw ingredient.
     *         Returns HTTP status code OK (200) if the raw ingredient is updated successfully.
     *         Returns HTTP status code NOT_FOUND (404) if the raw ingredient with the given ID is not found.
     */
    @PutMapping("/raw-ingredients-update-{id}")
    public ResponseEntity<RawIngredient> updateRawMaterial(@PathVariable Long id, @RequestBody RawIngredient rawIngredient) {
        RawIngredient updatedRawIngredient = rawIngredientService.updateRawMaterial(id, rawIngredient);
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
    @GetMapping("/raw-ingredients")
    public String showRawIngredientPage(Model model) {
        List<RawIngredient> ingredients = rawIngredientService.getAllRawMaterials() ;
        model.addAttribute("rawIngredients", ingredients);
        return "/raw-ingredients";
    }

    @GetMapping("/raw-ingredients/create")
    public String rawIngredientsCreate() {
        return "/raw-ingredients-create";
    }

    @GetMapping("/raw-ingredients/edit/{id}")
    public String updateRawMaterialForm(@PathVariable ("id") Long id, Model model) {
       RawIngredient rawIngredient = rawIngredientService.getRawMaterialById(id);
       model.addAttribute("rawIngredient", rawIngredient);
       return "/raw-ingredients-update";
    }

    //    @GetMapping("/raw-ingredients/delete")
//    public String rawIngredientsDelete() {
//        return "raw-ingredients-delete";
//    }

    @GetMapping("/raw-ingredients/delete/{id}")
    public String deleteRawIngredient(@PathVariable Long id) {
        rawIngredientService.deleteRawMaterial(id);
        return "redirect:/raw-ingredients";
    }

    @PostMapping("/raw-ingredients-update/{id}")
    public String updateRawIngredient(@PathVariable Long id, @ModelAttribute("raw-ingredient") RawIngredient rawingredient, Model model) {
        RawIngredient existingrawIngredient = rawIngredientService.getRawMaterialById(id);
        existingrawIngredient.setName(rawingredient.getName());
        existingrawIngredient.setDescription(rawingredient.getDescription());
        existingrawIngredient.setQuantity(rawingredient.getQuantity());

        rawIngredientService.updateRawIngredient(existingrawIngredient);
        return "redirect:/raw-ingredients";
    }
    @GetMapping("/raw-ingredients/sort-by-name")
    public String showIngredientsPageSorted(Model model) {
        List<RawIngredient> ingredients = rawIngredientService.getAllIngredientsByName();
        model.addAttribute("rawIngredients", ingredients);
        return "raw-ingredients";
    }
    @GetMapping("/raw-ingredients/sort-by-Quantity")
    public String showIngredientsPageSortedByQuantity(Model model) {
        List<RawIngredient> ingredients = rawIngredientService.getAllIngredientsByQuantity();
        model.addAttribute("rawIngredients", ingredients);
        return "raw-ingredients";
    }
}