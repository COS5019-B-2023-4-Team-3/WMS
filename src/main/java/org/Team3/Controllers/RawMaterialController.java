package org.Team3.Controllers;

import org.Team3.DTO.RawMaterialDto;
import org.Team3.Entities.RawMaterial;
import org.Team3.Services.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/raw-materials")
public class RawMaterialController {

    @Autowired
    private RawMaterialService rawMaterialService;

    @PostMapping
    public ResponseEntity<RawMaterial> createRawMaterial(@RequestBody RawMaterialDto rawMaterialDto) {
        RawMaterial createdRawMaterial = rawMaterialService.createRawMaterial(rawMaterialDto);
        return new ResponseEntity<>(createdRawMaterial, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RawMaterial> updateRawMaterial(@PathVariable Long id, @RequestBody RawMaterialDto rawMaterialDto) {
        RawMaterial updatedRawMaterial = rawMaterialService.updateRawMaterial(id, rawMaterialDto);
        if (updatedRawMaterial == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedRawMaterial, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRawMaterial(@PathVariable Long id) {
        boolean deleted = rawMaterialService.deleteRawMaterial(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

