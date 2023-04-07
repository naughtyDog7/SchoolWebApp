package uz.school.schoolwebapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.school.schoolwebapp.model.Material;
import uz.school.schoolwebapp.model.Subject;
import uz.school.schoolwebapp.model.Teacher;
import uz.school.schoolwebapp.service.MaterialService;
import uz.school.schoolwebapp.service.SubjectService;
import uz.school.schoolwebapp.service.TeacherService;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Controller
@RequestMapping("/materials")
public class MaterialsController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/")
    public String showMaterials(@RequestParam(name = "subjectId", required = false) Long subjectId, Model model) {
        List<Material> materialsList;
        List<Subject> subjectList = subjectService.getAllSubjects();
        if (subjectId != null) {
            materialsList = materialService.getMaterialsBySubject(subjectId);
        } else {
            materialsList = materialService.getAllMaterials();
        }
        model.addAttribute("materials", materialsList);
        model.addAttribute("subjects", subjectList);
        return "materials";
    }

    @GetMapping("/add")
    public String showAddMaterialForm(Model model) {
        List<Subject> subjectList = subjectService.getAllSubjects();
        List<Teacher> teachers = teacherService.getAllTeachers();
        model.addAttribute("material", new Material());
        model.addAttribute("subjects", subjectList);
        model.addAttribute("teachers", teachers);
        return "addMaterial";
    }

    @PostMapping(value = "/add", consumes = {MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("isAuthenticated()")
    public String addMaterial(@ModelAttribute("material") Material material,
                              BindingResult bindingResult,
                              @RequestParam("file") MultipartFile file,
                              Model model) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            material.setFile(bytes);
            material.setFilename(file.getOriginalFilename());
        }
        materialService.saveMaterial(material);
        return "redirect:/materials/";
    }

    @GetMapping("/download/{materialId}")
    public ResponseEntity<byte[]> downloadMaterial(@PathVariable Long materialId) {
        Material material = materialService.getMaterialById(materialId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(material.getFilename()).build());
        headers.setContentLength(material.getFile().length);
        return new ResponseEntity<>(material.getFile(), headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{materialId}")
    public String deleteMaterial(@PathVariable Long materialId) {
        materialService.deleteMaterialById(materialId);
        return "redirect:/materials/";
    }
}
