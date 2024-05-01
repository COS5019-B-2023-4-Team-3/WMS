package org.Team3.Services;

import org.Team3.Entities.RawIngredient;
import org.Team3.Repositories.RawIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RawIngredientService is a service class responsible for handling operations related to raw ingredients.
 * It interacts with the RawIngredientRepository to perform CRUD (Create, Read, Update, Delete) operations on raw ingredients.
 */
@Service
public class    RawIngredientService {
    @Autowired
    private RawIngredientRepository rawIngredientRepository;

    /**
     * Retrieves all raw materials from the repository.
     * @return List of all raw ingredients
     */
    public List<RawIngredient> getAllRawMaterials() {
        return rawIngredientRepository.findAll();
    }

    /**
     * Retrieves a raw material by its ID.
     * @param id The ID of the raw material to retrieve
     * @return The raw ingredient if found, otherwise null
     */
    public RawIngredient getRawMaterialById(Long id) {
        return rawIngredientRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new raw material based on the provided RawMaterialDto.
     * @param rawIngredient The DTO containing raw material details
     * @return The newly created raw ingredient
     */
    public RawIngredient createRawMaterial(RawIngredient rawIngredient) {
        // may want to check if the product already exists before saving it to the database
        return rawIngredientRepository.save(rawIngredient);
    }


    /**
     * Deletes a raw material with the provided ID.
     * @param id The ID of the raw material to delete
     * @return True if the raw material was successfully deleted, otherwise false
     */
    public boolean deleteRawMaterial(Long id) {
        if (!rawIngredientRepository.existsById(id)) {
            return false; // Raw material with given id does not exist
        }
        rawIngredientRepository.deleteById(id);
        return true;
    }

    /**
     * Updates an existing raw material with the provided ID using the details from the RawMaterialDto.
     * @param rawIngredient The RawIngredient containing updated raw material details
     * @return The updated raw ingredient if successful, otherwise null
     */
    public RawIngredient updateRawIngredient(RawIngredient rawIngredient){
        RawIngredient existingRawIngredient = rawIngredientRepository.findById(rawIngredient.getId()).orElse(null);
        if (existingRawIngredient == null) {
            return null; // Raw material with given id does not exist
        }
        return rawIngredientRepository.save(rawIngredient);
    }

    /**
     * Retrieves all raw ingredients sorted by name in ascending order.
     * @return List of all raw ingredients sorted by name
     */
    public List<RawIngredient> getAllIngredientsByName() {
        return rawIngredientRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
    }

    /**
     * Retrieves all raw ingredients sorted by quantity in ascending order.
     * @return List of all raw ingredients sorted by quantity
     */
    public List<RawIngredient> getAllIngredientsByQuantity() {
        return rawIngredientRepository.findAll(Sort.by(Sort.Direction.ASC,"quantity"));
    }
}

