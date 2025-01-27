package cz.esnhk.cds.model.cards;

import jakarta.persistence.*;

@Entity
@Table(name = "sim_cards")
public class SIMCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String simCardNumber;
    private String dateOfImport;
    private String dateOfIssue;
    private int issuedBy;
    private int importedBy;

    private CardStatusType cardStatus;

    public boolean isValid() {
        return true;
    }

    public SIMCard() {
    }

    public SIMCard(String simCardNumber, String dateOfImport, String dateOfIssue, CardStatusType cardStatus) {
        this.simCardNumber = simCardNumber;
        this.dateOfImport = dateOfImport;
        this.dateOfIssue = dateOfIssue;
        this.cardStatus = cardStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSimCardNumber() {
        return simCardNumber;
    }

    public void setSimCardNumber(String simCardNumber) {
        this.simCardNumber = simCardNumber;
    }

    public String getDateOfImport() {
        return dateOfImport;
    }

    public void setDateOfImport(String dateOfImport) {
        this.dateOfImport = dateOfImport;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public CardStatusType getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatusType cardStatus) {
        this.cardStatus = cardStatus;
    }

    public int getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(int issuedBy) {
        this.issuedBy = issuedBy;
    }

    public int getImportedBy() {
        return importedBy;
    }

    public void setImportedBy(int importedBy) {
        this.importedBy = importedBy;
    }
}
