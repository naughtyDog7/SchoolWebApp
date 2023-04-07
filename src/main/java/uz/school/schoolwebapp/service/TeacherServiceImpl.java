package uz.school.schoolwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.school.schoolwebapp.model.Teacher;
import uz.school.schoolwebapp.repository.TeachersRepository;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeachersRepository repository;

    @Override
    public List<Teacher> getAllTeachers() {
        return repository.findAll();

    }

    @Override
    public void saveTeacher(Teacher teacher) {
        repository.save(teacher);
    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
        return repository.findById(teacherId).orElse(null);
    }

    @Override
    public void deleteTeacherById(Long teacherId) {
        repository.deleteById(teacherId);
    }
}
