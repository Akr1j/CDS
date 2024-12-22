package cz.esnhk.cds.controller;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.service.esnCards.EsnCardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ESNcards/")
public class EsnCardsManagementController {

    private EsnCardService esnCardService;

    public EsnCardsManagementController(EsnCardService esnCardService) {
        this.esnCardService = esnCardService;
    }

    @RequestMapping("/")
    public String list(Model model) {
        Status status = new Status();
        status.setAvailableCards(esnCardService.getAvailableEsnCards().size());
        String formatedTodayDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        List<ESNcard> todayIssued = esnCardService.getEsnCardsByDateOfIssue(formatedTodayDate);
        status.setIssuedToday(todayIssued.size());
        status.setInternationalStudents(esnCardService.getESNCardsByDateOfIssueInternationalStudents(formatedTodayDate).size());
        status.setMembers(esnCardService.getESNCardsByDateOfIssueMembers(formatedTodayDate).size());

        //TODO: update dynamically
        status.todayIncome = todayIssued.size() * 400;

        model.addAttribute("esn_cards", esnCardService.getAllEsnCards());
        model.addAttribute("currentSemester", "2025/2025");
        model.addAttribute("semester", "2025/2025");
        model.addAttribute("stats", status);
        model.addAttribute("semesters", new String[]{"2021/2022", "2022/2023", "2023/2024", "2024/2025"});
        return "esn_cards/esn_cards_list";
    }

    @PostMapping("/find")
    public String find(Model model, @ModelAttribute String cardNumber) {
        //TODO
        ESNcard esNcard = esnCardService.getEsnCardByCardNumber(cardNumber);
        return null;
    }

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("newCard", new ESNcard());
        return "esn_cards/esn_card-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute ESNcard newCard) {
        if (esnCardService.getEsnCardByCardNumber(newCard.getCardNumber()) != null) {
            //TODO: error message
            System.out.println("Card is already in database");
            return "redirect:/ESNcards/";
        }

        String formatedTodayDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        //TODO: check if card number is valid / not used - ESN International
        ESNcard normalisedCard = new ESNcard(newCard.getCardNumber(), formatedTodayDate, null, newCard.getCardPrice());
        esnCardService.addEsnCard(normalisedCard);
        return "redirect:/ESNcards/";
    }

    @RequestMapping("/edit/{id}")
    public String edit() {
        return "esn_cards/esn_card_edit";
    }

    @RequestMapping("/delete/{id}")
    public String delete() {
        return "esn_cards/esn_card_delete";
    }

    @RequestMapping("/issue/{id}")
    public String issue() {
        return "esn_cards/esn_card_issue";
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
