package cz.esnhk.cds.model.users;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class Member extends User {
    private String section;
    //May be changed to enum / classes
    private String Role;
    private String aboutMe;

    public Member() {
    }

    public Member(long id, String name, String surname, String middleName, String email, String phone, String dayJoined, ESNcard esnCard, SIMCard simCard, String section, String role, boolean welcomePack, String aboutMe) {
        super(id, name, surname, middleName, email, phone, dayJoined, esnCard, simCard, welcomePack);
        this.section = section;
        Role = role;
        this.aboutMe = aboutMe;
    }

    //TODO unify with international student
    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
}
