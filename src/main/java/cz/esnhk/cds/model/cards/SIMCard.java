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

    public boolean isValid() {
        return true;
    }
}
