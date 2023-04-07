package uz.school.schoolwebapp.service;

import uz.school.schoolwebapp.model.Student;
import uz.school.schoolwebapp.model.SubjectReport;

import java.util.Map;

public interface ReportService {
    Map<Student, SubjectReport> getStatisticsPerStudent(Long subjectId);
}
