package cz.esnhk.cds.model.users;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
public class Member extends User {
    private String section;
    //May be changed to enum / classes
    private String Role;
    //TODO unify about me with international student
    private String aboutMe;

    public Member(long id, String name, String surname, String middleName, String email, String phone, String dayJoined, ESNcard esnCard, SIMCard simCard, String section, String role, boolean welcomePack, String aboutMe) {
        super(id, name, surname, middleName, email, phone, dayJoined, esnCard, simCard, welcomePack);
        this.section = section;
        Role = role;
        this.aboutMe = aboutMe;
    }
}
