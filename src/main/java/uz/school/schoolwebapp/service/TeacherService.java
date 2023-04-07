package uz.school.schoolwebapp.service;

import uz.school.schoolwebapp.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();

    void saveTeacher(Teacher teacher);

    Teacher getTeacherById(Long teacherId);

    void deleteTeacherById(Long teacherId);
}
