package cz.esnhk.cds.controller;

import cz.esnhk.cds.model.Semester;
import cz.esnhk.cds.model.cards.CardStatusType;
import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import cz.esnhk.cds.model.users.InternationalStudent;
import cz.esnhk.cds.model.users.Member;
import cz.esnhk.cds.service.InternationalStudents.InternationalStudentService;
import cz.esnhk.cds.service.Members.MemberService;
import cz.esnhk.cds.service.card.esnCards.EsnCardService;
import cz.esnhk.cds.service.card.simCards.SimCardService;
import cz.esnhk.cds.service.semesters.SemesterImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class InternationalStudentsController {

    private final EsnCardService esnCardService;
    private final SimCardService simCardService;
    private final InternationalStudentService internationalStudentService;
    private final SemesterImpl semesterImpl;
    private final MemberService memberService;

    public InternationalStudentsController(InternationalStudentService internationalStudentService, EsnCardService esnCardService, SimCardService simCardService, SemesterImpl semesterImpl, MemberService memberService) {
        this.internationalStudentService = internationalStudentService;
        this.esnCardService = esnCardService;
        this.simCardService = simCardService;
        this.semesterImpl = semesterImpl;
        this.memberService = memberService;
    }

    @RequestMapping("/")
    public String list(Model model) {
        Semester selectedSemester;
        List<Semester> semesters;
        List<InternationalStudent> international_students;

        if (model.containsAttribute("selectedSemester")) {
            selectedSemester = (Semester) model.getAttribute("selectedSemester");
        } else {
            selectedSemester = semesterImpl.getCurrentSemester();
        }
        if (selectedSemester == null) {
            selectedSemester = new Semester();
            selectedSemester.setId(0);
        }

        semesters = semesterImpl.getAllSemesters();

        if (model.containsAttribute("country")) {
            String country = Objects.requireNonNull(model.getAttribute("country")).toString();
            international_students = internationalStudentService.getAllInternationalStudents(selectedSemester.getId(), country.toUpperCase());
        } else {
            international_students = internationalStudentService.getAllInternationalStudents(selectedSemester.getId());
        }

        List<Country> countries = new ArrayList<>();
        Set<String> uniqueValues = new HashSet<>();
        for (InternationalStudent international_student : international_students) {
            String country = international_student.getCountryCode();
            if (uniqueValues.add(country)) {
                Country countryObj = new Country(country
                        , international_student.getCountry());
                countries.add(countryObj);
            }
        }

        model.addAttribute("international_students", international_students);
        model.addAttribute("semesters", semesters);
        model.addAttribute("currentSemester", selectedSemester);
        model.addAttribute("countries", countries);
        model.addAttribute("contentPage", "international_students/international_student_list");
        return "base";
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
            model.addAttribute("contentPage", "international_students/international_student_profile");
            return "base";
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
            model.addAttribute("contentPage", "international_students/international_student_add_esn_card");
            return "base";
        }
        return "redirect:/";
    }

    //TODO move to card controller?
    @PostMapping("intStudent/profile/{id}/assignESNcard/")
    public String addESNcard(@PathVariable long id, @RequestParam("esnCardId") long esnCardId) {
        InternationalStudent internationalStudent = internationalStudentService.getInternationalStudentById(id);
        ESNcard esnCard = esnCardService.getEsnCardById(esnCardId);
        esnCard.setDateOfIssue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        esnCard.setCardStatus(CardStatusType.ISSUED);

        SecurityContext context = SecurityContextHolder.getContext();
        int issuerId = Integer.parseInt(context.getAuthentication().getPrincipal().toString());
        esnCard.setIssuedBy(issuerId);
        Member issuer = memberService.getMemberById(issuerId);
        esnCard.setIssuedByName(issuer.getName() + " " + issuer.getSurname());


        if (internationalStudent != null) {
            internationalStudentService.addESNcard(id, esnCard);
            return "redirect:/intStudent/profile/" + id;
        }
        return "redirect:/501";
    }

    @GetMapping("intStudent/profile/{id}/assignSIMCard/")
    public String assignSIMCard(Model model, @PathVariable long id) {
        InternationalStudent internationalStudent = internationalStudentService.getInternationalStudentById(id);
        if (internationalStudent != null) {
            model.addAttribute("student", internationalStudent);
            //TODO: get only ESN cards that are not assigned to any student
            model.addAttribute("simCards", simCardService.getAvailableSimCards());
            model.addAttribute("simCardId", null);
            model.addAttribute("contentPage", "international_students/international_student_add_sim_card");
            return "base";
        }
        return "redirect:/";
    }

    @PostMapping("intStudent/profile/{id}/assignSIMCard/")
    public String assignSIMCard(@PathVariable long id, @RequestParam("simCardId") long simCardId) {
        InternationalStudent internationalStudent = internationalStudentService.getInternationalStudentById(id);
        SIMCard simCard = simCardService.getSimCardById(simCardId);
        simCard.setDateOfIssue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        simCard.setCardStatus(CardStatusType.ISSUED);

        SecurityContext context = SecurityContextHolder.getContext();
        int issuerId = Integer.parseInt(context.getAuthentication().getPrincipal().toString());
        simCard.setIssuedBy(issuerId);

        if (internationalStudent != null) {
            internationalStudentService.assignSimCard(id, simCard);
            return "redirect:/intStudent/profile/" + id;
        }
        return "redirect:/501";
    }

    @PostMapping("intStudent/search")
    public String search(RedirectAttributes redirectAttributes,
                         @RequestParam("semester") int semester,
                         @RequestParam(value = "countrySearch", required = false) String country) {
        redirectAttributes.addFlashAttribute("selectedSemester", semesterImpl.getSemesterById(semester));
        redirectAttributes.addFlashAttribute("country", country);
        return "redirect:/";
    }

    @Data
    @AllArgsConstructor
    private static class Country {
        private String code;
        private String name;
    }
}
