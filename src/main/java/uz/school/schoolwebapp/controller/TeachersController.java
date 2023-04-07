package uz.school.schoolwebapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.school.schoolwebapp.model.Teacher;
import uz.school.schoolwebapp.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeachersController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/")
    public String showTeachers(Model model) {
        List<Teacher> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }

    @GetMapping("/add")
    public String showAddTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "addTeacher";
    }

    @PostMapping("/add")
    public String addTeacher(@ModelAttribute("teacher") @Valid Teacher teacher,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "addTeacher";
        }
        teacherService.saveTeacher(teacher);
        return "redirect:/teachers/";
    }

    @PostMapping("/edit/{teacherId}")
    public String updateTeacher(@PathVariable Long teacherId,
                                @ModelAttribute("teacher") @Valid Teacher teacher,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "editTeacher";
        }
        teacher.setId(teacherId);
        teacherService.saveTeacher(teacher);
        return "redirect:/teachers/";
    }

    @GetMapping("/delete/{teacherId}")
    public String deleteTeacher(@PathVariable Long teacherId) {
        teacherService.deleteTeacherById(teacherId);
        return "redirect:/teachers/";
    }
}
