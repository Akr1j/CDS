package cz.esnhk.cds.controller.cards;

import lombok.Data;

@Data
class Status {
    private int todayIncome;
    private int issuedToday;
    private int internationalStudents;
    private int members;
    private int availableCards;
}
