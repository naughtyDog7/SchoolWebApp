package uz.school.schoolwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.school.schoolwebapp.model.Teacher;

public interface TeachersRepository extends JpaRepository<Teacher, Long> {
}
