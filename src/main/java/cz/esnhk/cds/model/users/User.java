package cz.esnhk.cds.model.users;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;

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
    private int phone;
    private String dayJoined;

    private boolean esnCard;
    private boolean simCard;
    private boolean welcomePack;

    public User(long id, String name, String surname, String middleName, String email, int phone, String dayJoined, boolean esnCard, boolean simCard, boolean welcomePack) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.email = email;
        this.phone = phone;
        this.dayJoined = dayJoined;
        this.esnCard = esnCard;
        this.simCard = simCard;
        this.welcomePack = welcomePack;

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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDayJoined() {
        return dayJoined;
    }

    public void setDayJoined(String dayJoined) {
        this.dayJoined = dayJoined;
    }

    public boolean esnCard() {
        return esnCard;
    }

    public void setEsnCard(boolean esnCard) {
        this.esnCard = esnCard;
    }

    public boolean simCard() {
        return simCard;
    }

    public void setSimCard(boolean simCard) {
        this.simCard = simCard;
    }

    public boolean welcomePack() {
        return welcomePack;
    }

    public void setWelcomePack(boolean welcomePack) {
        this.welcomePack = welcomePack;
    }
}
