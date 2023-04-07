package uz.school.schoolwebapp.service;

import uz.school.schoolwebapp.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student getStudentById(Long id);

    void saveStudent(Student student);

    void deleteStudentById(Long id);
}
