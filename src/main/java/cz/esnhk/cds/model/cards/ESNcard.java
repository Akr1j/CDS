package cz.esnhk.cds.model.cards;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "esncards")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ESNcard extends Card {
    private int cardPrice;
    private CardStatusType cardStatus;

    public ESNcard(String cardNumber, String dateOfImport, String dateOfIssue, int cardPrice) {
        super(cardNumber, dateOfImport, dateOfIssue);
        this.cardPrice = cardPrice;
        this.cardStatus = CardStatusType.AVAILABLE;
        //TODO: check status of card
    }

    public boolean isValid() {
        return true;
    }
}
