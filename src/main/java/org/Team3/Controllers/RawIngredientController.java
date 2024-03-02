package org.Team3.Controllers;

import org.Team3.DTO.RawMaterialDto;
import org.Team3.Entities.RawIngredient;
import org.Team3.Services.RawIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/raw-materials")
public class RawIngredientController {

    @Autowired
    private RawIngredientService rawIngredientService;

    @PostMapping
    public ResponseEntity<RawIngredient> createRawMaterial(@RequestBody RawMaterialDto rawMaterialDto) {
        RawIngredient createdRawIngredient = rawIngredientService.createRawMaterial(rawMaterialDto);
        return new ResponseEntity<>(createdRawIngredient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RawIngredient> updateRawMaterial(@PathVariable Long id, @RequestBody RawMaterialDto rawMaterialDto) {
        RawIngredient updatedRawIngredient = rawIngredientService.updateRawMaterial(id, rawMaterialDto);
        if (updatedRawIngredient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedRawIngredient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRawMaterial(@PathVariable Long id) {
        boolean deleted = rawIngredientService.deleteRawMaterial(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

