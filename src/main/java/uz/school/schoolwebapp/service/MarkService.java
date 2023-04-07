package uz.school.schoolwebapp.service;


import uz.school.schoolwebapp.model.Mark;

import java.util.List;

public interface MarkService {
    List<Mark> getAllMarks();

    void saveMark(Mark mark);

    void deleteMarkById(Long id);
}
