package uz.school.schoolwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.school.schoolwebapp.model.Student;
import uz.school.schoolwebapp.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void saveStudent(Student student) {
        repository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        repository.deleteById(id);
    }
}
