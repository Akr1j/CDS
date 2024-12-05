package cz.esnhk.cds.model.cards;

import jakarta.persistence.*;

@Entity
@Table(name = "esncards")
public class ESNcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String cardNumber;
    private String dateOfImport;
    private String dateOfIssue;
    private int cardPrice;

}
