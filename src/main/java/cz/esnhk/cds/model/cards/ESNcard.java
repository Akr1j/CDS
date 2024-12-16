package cz.esnhk.cds.model.cards;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "esncards")
public class ESNcard {

    private enum CardStatusType {ACTIVE, INACTIVE};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cardNumber;
    @NotBlank
    private String dateOfImport;
    private String dateOfIssue;
    private int cardPrice;
    private CardStatusType cardStatus;

    public ESNcard() {
    }

    public ESNcard(String cardNumber, String dateOfImport, String dateOfIssue, int cardPrice) {
        this.cardNumber = cardNumber;
        this.dateOfImport = dateOfImport;
        this.dateOfIssue = dateOfIssue;
        this.cardPrice = cardPrice;
        this.cardStatus = CardStatusType.ACTIVE;
    }

    public long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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

    public int getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(int cardPrice) {
        this.cardPrice = cardPrice;
    }

    public boolean isValid() {
        return true;
    }

    public CardStatusType getCardStatus() {;
        return cardStatus;
    }

    public void setCardStatus(CardStatusType cardStatus) {
        this.cardStatus = cardStatus;
    }

    @Override
    public String toString() {
        return "ESNcard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", dateOfImport='" + dateOfImport + '\'' +
                ", dateOfIssue='" + dateOfIssue + '\'' +
                ", cardPrice=" + cardPrice +
                ", cardStatus=" + cardStatus +
                '}';
    }
}
