package cz.esnhk.cds.controller;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.users.InternationalStudent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "international_students/international_student_list";
    }

    @GetMapping("/intStudent/add")
    public String add(Model model) {
        model.addAttribute("international_student", new InternationalStudent());
        return "international_students/international_student_add";
    }

    @PostMapping("/intStudent/add")
    public String add(@ModelAttribute InternationalStudent internationalStudent) {
        internationalStudentService.addInternationalStudent(internationalStudent);
        return "redirect:/";
    }

    @GetMapping("/intStudent/profile/{id}")
    public String profile(Model model, @PathVariable long id) {
        InternationalStudent internationalStudent = internationalStudentService.getInternationalStudentById(id);
        if (internationalStudent != null) {
            model.addAttribute("student", internationalStudent);
            return "international_students/international_student_profile";
        }
        return "redirect:/";
    }

    @GetMapping("intStudent/profile/{id}/addESNcard/")
    public String addESNcard(Model model, @PathVariable long id) {
        InternationalStudent internationalStudent = internationalStudentService.getInternationalStudentById(id);
        ESNcard esnCard = new ESNcard();
        if (internationalStudent != null) {
            model.addAttribute("student", internationalStudent);
            model.addAttribute("esnCard", esnCard);
            return "international_students/international_student_add_esn_card";
        }
        return "redirect:/";
    }

    @PostMapping("intStudent/profile/{id}/addESNcard/")
    public String addESNcard(@ModelAttribute ESNcard esnCard, @PathVariable long id) {
        InternationalStudent internationalStudent = internationalStudentService.getInternationalStudentById(id);
        if (internationalStudent != null) {
            internationalStudentService.addESNcard(id, esnCard);
            return "redirect:/intStudent/profile/" + id;
        }
        return "redirect:/501";
    }
}
