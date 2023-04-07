package uz.school.schoolwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.school.schoolwebapp.model.Subject;
import uz.school.schoolwebapp.repository.SubjectRepository;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository repository;

    @Override
    public List<Subject> getAllSubjects() {
        return repository.findAll();
    }

    @Override
    public Subject getById(Long subjectId) {
        return repository.findById(subjectId).orElse(null);
    }

    @Override
    public void saveSubject(Subject subject) {
        repository.save(subject);
    }
}
