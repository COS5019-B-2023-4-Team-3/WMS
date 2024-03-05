package org.Team3.Services;

import org.Team3.DTO.RawMaterialDto;
import org.Team3.Entities.RawIngredient;
import org.Team3.Repositories.RawIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawIngredientService {
    @Autowired
    private RawIngredientRepository rawIngredientRepository;

    public List<RawIngredient> getAllRawMaterials() {
        return rawIngredientRepository.findAll();
    }

    public RawIngredient getRawMaterialById(Long id) {
        return rawIngredientRepository.findById(id).orElse(null);
    }

    public RawIngredient createRawMaterial(RawMaterialDto rawMaterialDto) {
        RawIngredient rawIngredient = new RawIngredient(rawMaterialDto.getName(), rawMaterialDto.getQuantity());
        return rawIngredientRepository.save(rawIngredient);
    }

    public RawIngredient updateRawMaterial(Long id, RawMaterialDto rawMaterialDto) {
        RawIngredient existingRawIngredient = rawIngredientRepository.findById(id).orElse(null);
        if (existingRawIngredient == null) {
            return null; // Raw material with given id does not exist
        }

        existingRawIngredient.setName(rawMaterialDto.getName());
        existingRawIngredient.setQuantity(rawMaterialDto.getQuantity());
        return rawIngredientRepository.save(existingRawIngredient);
    }

    public boolean deleteRawMaterial(Long id) {
        if (!rawIngredientRepository.existsById(id)) {
            return false; // Raw material with given id does not exist
        }
        rawIngredientRepository.deleteById(id);
        return true;
    }
}

