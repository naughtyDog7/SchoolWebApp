package uz.school.schoolwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.school.schoolwebapp.model.Material;
import uz.school.schoolwebapp.repository.MaterialsRepository;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialsRepository repository;

    @Override
    public List<Material> getAllMaterials() {
        return repository.findAll();
    }

    @Override
    public void saveMaterial(Material material) {
        repository.save(material);
    }

    @Override
    public void deleteMaterialById(Long materialId) {
        repository.deleteById(materialId);
    }

    @Override
    public List<Material> getMaterialsBySubject(Long subjectId) {
        return repository.getMaterialsBySubjectId(subjectId);
    }

    @Override
    public Material getMaterialById(Long materialId) {
        return repository.findById(materialId).orElse(null);
    }
}
