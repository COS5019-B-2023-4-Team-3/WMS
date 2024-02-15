package org.Team3.Services;
import org.Team3.DTO.RawMaterialDto;
import org.Team3.Entities.RawMaterial;
import org.Team3.Repositories.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RawMaterialService {
    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    public List<RawMaterial> getAllRawMaterials() {
        return rawMaterialRepository.findAll();
    }

    public RawMaterial getRawMaterialById(Long id) {
        return rawMaterialRepository.findById(id).orElse(null);
    }

    public RawMaterial createRawMaterial(RawMaterialDto rawMaterialDto) {
        RawMaterial rawMaterial = new RawMaterial(rawMaterialDto.getName(), rawMaterialDto.getQuantity());
        return rawMaterialRepository.save(rawMaterial);
    }

    public RawMaterial updateRawMaterial(Long id, RawMaterialDto rawMaterialDto) {
        RawMaterial existingRawMaterial = rawMaterialRepository.findById(id).orElse(null);
        if (existingRawMaterial == null) {
            return null; // Raw material with given id does not exist
        }

        existingRawMaterial.setName(rawMaterialDto.getName());
        existingRawMaterial.setQuantity(rawMaterialDto.getQuantity());
        return rawMaterialRepository.save(existingRawMaterial);
    }

    public boolean deleteRawMaterial(Long id) {
        if (!rawMaterialRepository.existsById(id)) {
            return false; // Raw material with given id does not exist
        }
        rawMaterialRepository.deleteById(id);
        return true;
    }
}

