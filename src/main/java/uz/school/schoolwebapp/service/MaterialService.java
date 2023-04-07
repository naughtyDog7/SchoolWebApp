package uz.school.schoolwebapp.service;

import uz.school.schoolwebapp.model.Material;

import java.util.List;

public interface MaterialService {
    List<Material> getAllMaterials();

    void saveMaterial(Material material);

    Material getMaterialById(Long materialId);

    void deleteMaterialById(Long materialId);

    List<Material> getMaterialsBySubject(Long subjectId);
}
