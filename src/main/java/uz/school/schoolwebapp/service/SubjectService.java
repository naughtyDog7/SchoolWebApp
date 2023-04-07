package uz.school.schoolwebapp.service;

import uz.school.schoolwebapp.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();

    void saveSubject(Subject subject);

    Subject getById(Long subjectId);
}
