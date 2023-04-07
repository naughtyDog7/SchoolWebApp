package uz.school.schoolwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.school.schoolwebapp.model.Mark;
import uz.school.schoolwebapp.repository.MarkRepository;

import java.util.List;

@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    private MarkRepository markRepository;

    @Override
    public List<Mark> getAllMarks() {
        return markRepository.findAll();
    }

    @Override
    public void saveMark(Mark mark) {
        markRepository.save(mark);
    }

    @Override
    public void deleteMarkById(Long id) {
        markRepository.deleteById(id);
    }
}
