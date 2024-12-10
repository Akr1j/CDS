package cz.esnhk.cds.model.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "international_students")
public class InternationalStudent{

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
    private String aboutMe;

    private boolean esnCard;
    private boolean simCard;
    private boolean welcomePack;

    private String country;


    public InternationalStudent(long id, String name, String surname, String middleName, String email, int phone, String dayJoined, boolean esnCard, boolean simCard, boolean welcomePack, String country, String aboutMe) {
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
        this.country = country;
        this.aboutMe = aboutMe;
    }

    public InternationalStudent() {

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

    public boolean isEsnCard() {
        return esnCard;
    }

    public void setEsnCard(boolean esnCard) {
        this.esnCard = esnCard;
    }

    public boolean isSimCard() {
        return simCard;
    }

    public void setSimCard(boolean simCard) {
        this.simCard = simCard;
    }

    public boolean isWelcomePack() {
        return welcomePack;
    }

    public void setWelcomePack(boolean welcomePack) {
        this.welcomePack = welcomePack;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
}
