package cz.esnhk.cds.model.cards;

import cz.esnhk.cds.model.users.InternationalStudent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cardNumber;
    @NotBlank
    private String dateOfImport;
    private int importedBy;
    private String dateOfIssue;
    private int issuedBy;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private InternationalStudent ownerId;

    public Card(String cardNumber, String dateOfImport, String dateOfIssue) {
        this.cardNumber = cardNumber;
        this.dateOfImport = dateOfImport;
        this.dateOfIssue = dateOfIssue;
    }
}
