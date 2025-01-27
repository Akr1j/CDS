package cz.esnhk.cds.model.users;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import jakarta.persistence.*;

@Entity
@Table(name = "international_students")
public class InternationalStudent extends User {

    private String country;
    private String aboutMe;


    public InternationalStudent(long id, String name, String surname, String middleName, String email, String phone, String dayJoined, ESNcard esnCard, SIMCard simCard, boolean welcomePack, String country, String aboutMe) {
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

    @Override
    public String toString() {
        return "InternationalStudent{" +
                "country='" + country + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                '}'
                + super.toString();
    }

}
