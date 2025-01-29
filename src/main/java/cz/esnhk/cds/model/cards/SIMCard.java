package cz.esnhk.cds.model.cards;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sim_cards")
@Data
@NoArgsConstructor
public class SIMCard extends Card {

    private CardStatusType cardStatus;

    public boolean isValid() {
        return true;
    }

    public SIMCard(String simCardNumber, String dateOfImport, String dateOfIssue, CardStatusType cardStatus) {
        super(simCardNumber, dateOfImport, dateOfIssue);
        this.cardStatus = cardStatus;
    }
}
