package uz.school.schoolwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.school.schoolwebapp.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
