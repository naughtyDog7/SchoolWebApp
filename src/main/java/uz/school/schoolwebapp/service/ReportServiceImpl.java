package uz.school.schoolwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.school.schoolwebapp.model.Mark;
import uz.school.schoolwebapp.model.Student;
import uz.school.schoolwebapp.model.SubjectReport;
import uz.school.schoolwebapp.repository.MarkRepository;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private SubjectService subjectService;

    public Map<Student, SubjectReport> getStatisticsPerStudent(Long subjectId) {
        List<Mark> marks = markRepository.findBySubjectId(subjectId);
        Map<Student, List<Mark>> marksByStudent = marks.stream()
                .collect(Collectors.groupingBy(Mark::getStudent));
        Map<Student, SubjectReport> statisticsByStudent = new HashMap<>();
        for (Map.Entry<Student, List<Mark>> entry : marksByStudent.entrySet()) {
            Student student = entry.getKey();
            DoubleSummaryStatistics stats = entry.getValue().stream()
                    .mapToDouble(Mark::getValue)
                    .summaryStatistics();
            SubjectReport subjectReport = new SubjectReport();
            subjectReport.setSubjectName(subjectService.getById(subjectId).getName());
            subjectReport.setMinMark(stats.getMin());
            subjectReport.setAvgMark(stats.getAverage());
            subjectReport.setMaxMark(stats.getMax());
            statisticsByStudent.put(student, subjectReport);
        }
        return statisticsByStudent;
    }
}
