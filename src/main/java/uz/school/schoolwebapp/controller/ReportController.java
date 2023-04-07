package uz.school.schoolwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.school.schoolwebapp.model.Student;
import uz.school.schoolwebapp.model.Subject;
import uz.school.schoolwebapp.model.SubjectReport;
import uz.school.schoolwebapp.service.ReportService;
import uz.school.schoolwebapp.service.SubjectService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/")
    public String getReportsPage(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "reports";
    }

    @GetMapping("/statistics")
    public String getStatistics(Model model, @RequestParam Long subjectId) {
        Map<Student, SubjectReport> statisticsPerStudent = reportService.getStatisticsPerStudent(subjectId);
        Subject subject = subjectService.getById(subjectId);
        model.addAttribute("subjectName", subject.getName());
        model.addAttribute("statisticsPerStudent", statisticsPerStudent);
        return "statistics";
    }
}
