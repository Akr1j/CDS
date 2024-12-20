package cz.esnhk.cds.controller;

import cz.esnhk.cds.model.cards.CardStatusType;
import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.users.InternationalStudent;
import cz.esnhk.cds.service.InternationalStudentService;
import cz.esnhk.cds.service.esnCards.EsnCardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/")
public class InternationalStudentsController {

    private final EsnCardService esnCardService;
    private InternationalStudentService internationalStudentService;

    public InternationalStudentsController(InternationalStudentService internationalStudentService, EsnCardService esnCardService) {
        this.internationalStudentService = internationalStudentService;
        this.esnCardService = esnCardService;
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
        //TODO: not found page
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
        if (internationalStudent != null) {
            model.addAttribute("student", internationalStudent);
            //TODO: get only ESN cards that are not assigned to any student
            model.addAttribute("esnCards", esnCardService.getAvailableEsnCards());
            model.addAttribute("esnCardId", null);
            return "international_students/international_student_add_esn_card";
        }
        return "redirect:/";
    }

    @PostMapping("intStudent/profile/{id}/assignESNcard/")
    public String addESNcard(@PathVariable long id, @RequestParam("esnCardId") long esnCardId) {
        InternationalStudent internationalStudent = internationalStudentService.getInternationalStudentById(id);
        ESNcard esnCard = esnCardService.getEsnCardById(esnCardId);
        esnCard.setDateOfIssue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        esnCard.setCardStatus(CardStatusType.ISSUED);
        if (internationalStudent != null && esnCard != null) {
            internationalStudentService.addESNcard(id, esnCard);
            return "redirect:/intStudent/profile/" + id;
        }
        return "redirect:/501";
    }
}
