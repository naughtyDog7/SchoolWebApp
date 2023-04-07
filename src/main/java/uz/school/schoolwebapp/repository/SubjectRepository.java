package uz.school.schoolwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.school.schoolwebapp.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
