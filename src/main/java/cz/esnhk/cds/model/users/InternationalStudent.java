package cz.esnhk.cds.model.users;

import jakarta.persistence.*;

@Entity
@Table(name = "international_students")
public class InternationalStudent extends User {

    private String country;
    private String aboutMe;


    public InternationalStudent(long id, String name, String surname, String middleName, String email, int phone, String dayJoined, boolean esnCard, boolean simCard, boolean welcomePack, String country, String aboutMe) {
        super(id, name, surname, middleName, email, phone, dayJoined, esnCard, simCard, welcomePack);
        this.country = country;
        this.aboutMe = aboutMe;
    }

    public InternationalStudent() {

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
