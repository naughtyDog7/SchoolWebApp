package uz.school.schoolwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.school.schoolwebapp.model.Material;
import uz.school.schoolwebapp.model.Subject;

import java.util.List;

public interface MaterialsRepository extends JpaRepository<Material, Long> {
    List<Material> getMaterialsBySubjectId(Long subjectId);
}
