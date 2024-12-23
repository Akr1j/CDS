package cz.esnhk.cds.model.users;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String faculty;

    @OneToMany
    private List<ESNcard> esnCards;
    @OneToMany
    private List<SIMCard> simCards;
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

    public User() {

    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDayJoined() {
        return dayJoined;
    }

    public void setDayJoined(String dayJoined) {
        this.dayJoined = dayJoined;
    }

    public boolean welcomePack() {
        return welcomePack;
    }

    public void setWelcomePack(boolean welcomePack) {
        this.welcomePack = welcomePack;
    }

    public List<ESNcard> getEsnCards() {
        return esnCards;
    }

    public List<SIMCard> getSimCards() {
        return simCards;
    }

    public void setSimCards(List<SIMCard> simCards) {
        this.simCards = simCards;
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

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
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
