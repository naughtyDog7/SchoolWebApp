package uz.school.schoolwebapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.school.schoolwebapp.model.Mark;
import uz.school.schoolwebapp.model.Student;
import uz.school.schoolwebapp.model.Subject;
import uz.school.schoolwebapp.service.MarkService;
import uz.school.schoolwebapp.service.StudentService;
import uz.school.schoolwebapp.service.SubjectService;

import java.util.List;

@Controller
@RequestMapping("/marks")
public class MarkController {
    @Autowired
    private MarkService markService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/")
    public String showMarks(Model model) {
        List<Mark> marks = markService.getAllMarks();
        model.addAttribute("marks", marks);
        return "marks";
    }

    @GetMapping("/add")
    public String showAddMarkForm(Model model) {
        List<Student> students = studentService.getAllStudents();
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("mark", new Mark());
        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        return "addMarks";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String addMark(@ModelAttribute("mark") @Valid Mark mark,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            List<Student> students = studentService.getAllStudents();
            List<Subject> subjects = subjectService.getAllSubjects();
            model.addAttribute("students", students);
            model.addAttribute("subjects", subjects);
            return "addMarks";
        }
        markService.saveMark(mark);
        return "redirect:/marks/";
    }

    @GetMapping("/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        markService.deleteMarkById(id);
        return "redirect:/marks/";
    }
}
