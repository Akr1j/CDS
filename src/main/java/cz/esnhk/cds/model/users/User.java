package cz.esnhk.cds.model.users;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //If Artemis synchronization is used, the id cant be generated
    private long id;

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private String middleName;
    @NotBlank
    private String email;
    private String phone;
    private String dayJoined;
    private String dateOfBirth;
    private String faculty;
    //TODO: Add sex, profile picture, staff, superuser, is_active, confirmed,

    @OneToMany
    private List<ESNcard> esnCards = new ArrayList<>();
    @OneToMany
    private List<SIMCard> simCards = new ArrayList<>();
    private boolean welcomePack;

    public User(long id, String name, String surname, String middleName, String email, String phone, String dayJoined, ESNcard esnCards, SIMCard simCard, boolean welcomePack) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.email = email;
        this.phone = phone;
        this.dayJoined = dayJoined;
        this.esnCards = null;
        this.simCards = null;
        this.welcomePack = false;
    }

    public boolean haveValidEsnCard() {
        if (esnCards == null)
            return false;
        for (ESNcard esnCard : esnCards) {
            if (esnCard.isValid())
                return true;
        }
        return false;
    }

    public boolean haveValidSimCard() {
        if (simCards == null)
            return false;
        for (SIMCard simCard : simCards) {
            if (simCard.isValid())
                return true;
        }
        return false;
    }

    public void addESNcard(ESNcard esnCard) {
        esnCards.add(esnCard);

    }

    public void addSimCard(SIMCard simCard) {
        simCards.add(simCard);
    }

    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dayJoined='" + dayJoined + '\'' +
                ", esnCards=" + esnCards +
                ", simCards=" + simCards +
                ", welcomePack=" + welcomePack +
                '}';
    }
}
