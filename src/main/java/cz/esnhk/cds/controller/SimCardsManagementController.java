package cz.esnhk.cds.controller;

import cz.esnhk.cds.model.cards.CardStatusType;
import cz.esnhk.cds.model.cards.SIMCard;
import cz.esnhk.cds.service.simCards.SimCardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/SIMCards/")
public class SimCardsManagementController {

    private final SimCardService simCardService;

    public SimCardsManagementController(SimCardService simCardService) {
        this.simCardService = simCardService;
    }

    @RequestMapping("/")
    public String list(Model model) {
        Status status = new Status();
        status.setAvailableCards(simCardService.getAvailableSimCards().size());
        String formatedTodayDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        List<SIMCard> todayIssued = simCardService.getSimCardsByDateOfIssue(formatedTodayDate);
        status.setIssuedToday(todayIssued.size());
        status.setInternationalStudents(simCardService.getSimCardsByDateOfIssueInternationalStudents(formatedTodayDate).size());
        status.setMembers(simCardService.getSimCardsByDateOfIssueMembers(formatedTodayDate).size());

        //TODO: update dynamically
        status.todayIncome = todayIssued.size() * 400;

        model.addAttribute("sim_cards", simCardService.getAllSimCards());
        model.addAttribute("currentSemester", "2025/2025");
        model.addAttribute("semester", "2025/2025");
        model.addAttribute("stats", status);
        model.addAttribute("semesters", new String[]{"2021/2022", "2022/2023", "2023/2024", "2024/2025"});
        return "sim_cards/sim_cards_list";
    }

    @PostMapping("/find")
    public String find(Model model, @ModelAttribute String cardNumber) {
        //TODO
        SIMCard simCard = simCardService.getSimCardByCardNumber(cardNumber);
        return null;
    }

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("newCard", new SIMCard());
        return "sim_cards/sim_card-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute SIMCard newCard) {
        if (simCardService.getSimCardByCardNumber(newCard.getSimCardNumber()) != null) {
            //TODO: error message
            System.out.println("Card is already in database");
            return "redirect:/SIMCards/";
        }

        String formatedTodayDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        SIMCard normalisedCard = new SIMCard(newCard.getSimCardNumber(), formatedTodayDate, null, CardStatusType.AVAILABLE);
        simCardService.addSimCard(normalisedCard);
        return "redirect:/SIMCards/";
    }

    @RequestMapping("/edit/{id}")
    public String edit() {
        return "sim_cards/sim_card_edit";
    }

    @RequestMapping("/delete/{id}")
    public String delete() {
        return "sim_cards/sim_card_delete";
    }

    @RequestMapping("/issue/{id}")
    public String issue() {
        return "sim_cards/sim_card_issue";
    }

    private static class Status {
        private int todayIncome;
        private int issuedToday;
        private int internationalStudents;
        private int members;
        private int availableCards;

        public int getIssuedToday() {
            return issuedToday;
        }

        public void setIssuedToday(int issuedToday) {
            this.issuedToday = issuedToday;
        }

        public int getTodayIncome() {
            return todayIncome;
        }

        public int getInternationalStudents() {
            return internationalStudents;
        }

        public int getMembers() {
            return members;
        }

        public int getAvailableCards() {
            return availableCards;
        }

        public void setAvailableCards(int availableCards) {
            this.availableCards = availableCards;
        }

        public void setInternationalStudents(int internationalStudents) {
            this.internationalStudents = internationalStudents;
        }

        public void setMembers(int members) {
            this.members = members;
        }
    }
}
