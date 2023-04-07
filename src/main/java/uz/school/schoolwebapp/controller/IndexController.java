package uz.school.schoolwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uz.school.schoolwebapp.service.MarkService;

@Controller
public class IndexController {

    @Autowired
    private MarkService markService;

    @GetMapping("/")
    public String getIndex(Model model) {
        return "index";
    }
}
