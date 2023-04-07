package uz.school.schoolwebapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.school.schoolwebapp.model.Subject;
import uz.school.schoolwebapp.service.SubjectService;

import java.util.List;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/")
    public String showSubjects(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subjects";
    }

    @GetMapping("/add")
    public String showAddSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "addSubject";
    }

    @PostMapping("/add")
    public String addSubject(@ModelAttribute("subject") @Valid Subject subject,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "addSubject";
        }
        subjectService.saveSubject(subject);
        return "redirect:/subjects/";
    }

}
