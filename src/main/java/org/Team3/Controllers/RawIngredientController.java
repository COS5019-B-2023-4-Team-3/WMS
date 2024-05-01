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
 * RawIngredientController class defines endpoints to handle CRUD (Create, Read, Update, Delete) operations related to raw ingredients.
 * This controller is responsible for processing HTTP requests and returning appropriate responses for operations
 * on raw ingredients such as creating a new raw ingredient, updating an existing raw ingredient, and deleting a raw ingredient.
 */
@Controller

public class RawIngredientController {


    private final RawIngredientService rawIngredientService;

    /**
     * Constructor for RawIngredientController.
     *
     * @param rawIngredientService The service to be used by this controller.
     */
    public RawIngredientController(RawIngredientService rawIngredientService){
        this.rawIngredientService = rawIngredientService;
    }

    /**
     * Endpoint for displaying the raw ingredients page.
     *
     * @param model The model to add attributes to.
     * @return The raw ingredients page.
     */
    @GetMapping("/raw-ingredients")
    public String showRawIngredientPage(Model model) {
        List<RawIngredient> ingredients = rawIngredientService.getAllRawMaterials() ;
        model.addAttribute("rawIngredients", ingredients);
        return "/raw-ingredients";
    }

    /**
     * Endpoint for creating a new raw ingredient.
     *
     * @param rawIngredient The raw ingredient to create.
     * @param model The model to add attributes to.
     * @return A redirect to the raw ingredients page.
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
     * Endpoint for displaying the raw ingredients creation page.
     *
     * @return The raw ingredients creation page.
     */
    @GetMapping("/raw-ingredients/create")
    public String rawIngredientsCreate() {
        return "/raw-ingredients-create";
    }

    /**
     * Endpoint for displaying the raw ingredients update form.
     *
     * @param id The id of the raw ingredient to update.
     * @param model The model to add attributes to.
     * @return The raw ingredients update form.
     */
    @GetMapping("/raw-ingredients/edit/{id}")
    public String updateRawMaterialForm(@PathVariable ("id") Long id, Model model) {
       RawIngredient rawIngredient = rawIngredientService.getRawMaterialById(id);
       model.addAttribute("rawIngredient", rawIngredient);
       return "/raw-ingredients-update";
    }


    /**
     * Endpoint for updating a raw ingredient.
     *
     * @param id The id of the raw ingredient to update.
     * @param rawingredient The updated raw ingredient.
     * @param model The model to add attributes to.
     * @return A redirect to the raw ingredients page.
     */
    @PostMapping("/raw-ingredients-update/{id}")
    public String updateRawIngredient(@PathVariable Long id, @ModelAttribute("raw-ingredient") RawIngredient rawingredient, Model model) {
        RawIngredient existingrawIngredient = rawIngredientService.getRawMaterialById(id);
        existingrawIngredient.setName(rawingredient.getName());
        existingrawIngredient.setDescription(rawingredient.getDescription());
        existingrawIngredient.setQuantity(rawingredient.getQuantity());

        rawIngredientService.updateRawIngredient(existingrawIngredient);
        return "redirect:/raw-ingredients";
    }

    /**
     * Endpoint for deleting a raw ingredient.
     *
     * @param id The id of the raw ingredient to delete.
     * @return A redirect to the raw ingredients page.
     */
    @GetMapping("/raw-ingredients/delete/{id}")
    public String deleteRawIngredient(@PathVariable Long id) {
        rawIngredientService.deleteRawMaterial(id);
        return "redirect:/raw-ingredients";
    }

    /**
     * Endpoint for deleting a raw ingredient.
     *
     * @param id The id of the raw ingredient to delete.
     * @return A ResponseEntity with the appropriate status code.
     */
    @DeleteMapping("/raw-ingredients-delete-{id}")
    public ResponseEntity<Void> deleteRawMaterial(@PathVariable Long id) {
        boolean deleted = rawIngredientService.deleteRawMaterial(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint for displaying the raw ingredients page sorted by name.
     *
     * @param model The model to add attributes to.
     * @return The raw ingredients page sorted by name.
     */
    @GetMapping("/raw-ingredients/sort-by-name")
    public String showIngredientsPageSorted(Model model) {
        List<RawIngredient> ingredients = rawIngredientService.getAllIngredientsByName();
        model.addAttribute("rawIngredients", ingredients);
        return "raw-ingredients";
    }

    /**
     * Endpoint for displaying the raw ingredients page sorted by quantity.
     *
     * @param model The model to add attributes to.
     * @return The raw ingredients page sorted by quantity.
     */
    @GetMapping("/raw-ingredients/sort-by-Quantity")
    public String showIngredientsPageSortedByQuantity(Model model) {
        List<RawIngredient> ingredients = rawIngredientService.getAllIngredientsByQuantity();
        model.addAttribute("rawIngredients", ingredients);
        return "raw-ingredients";
    }
}