package cz.esnhk.cds.controller;

import cz.esnhk.cds.model.users.InternationalStudent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.esnhk.cds.service.InternationalStudentService;

@Controller
@RequestMapping("/")
public class InternationalStudentsController {

    private InternationalStudentService internationalStudentService;

    public InternationalStudentsController(InternationalStudentService internationalStudentService) {
        this.internationalStudentService = internationalStudentService;
    }

    @RequestMapping("/")
    public String list(Model model) {
        model.addAttribute("international_students", internationalStudentService.getAllInternationalStudents());
        return "international_student_list";
    }

    @GetMapping("/intStudent/add")
    public String add(Model model) {
        model.addAttribute("international_student", new InternationalStudent());
        return "international_student_add";
    }

    @PostMapping("/intStudent/add")
    public String add(@ModelAttribute InternationalStudent internationalStudent) {
        System.out.println(internationalStudent.toString());
        internationalStudentService.addInternationalStudent(internationalStudent);
        return "redirect:/";
    }
}
