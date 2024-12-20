package cz.esnhk.cds.controller;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.service.esnCards.EsnCardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ESNcards/")
public class EsnCardsManagementController {

    private static class Status{
        private int issuedToday = 1;
        private int todayIncome = 400;
        private int internationalStudents = 1;
        private int members = 0;
        private int availableCards = 224;

        public int getIssuedToday() {
            return issuedToday;
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
    }

    private EsnCardService esnCardService;

    public EsnCardsManagementController(EsnCardService esnCardService) {
        this.esnCardService = esnCardService;
    }

    @RequestMapping("/")
    public String list(Model model) {
        model.addAttribute("esn_cards", esnCardService.getAllEsnCards());
        model.addAttribute("currentSemester", "2025/2025");
        model.addAttribute("semester", "2025/2025");
        model.addAttribute("stats", new Status());
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
    public String add() {
        return "esn_cards/esn_card_add";
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
}
