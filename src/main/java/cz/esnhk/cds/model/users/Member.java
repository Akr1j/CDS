package cz.esnhk.cds.model.users;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class Member extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String section;
    //May be changed to enum / classes
    private String Role;

    public Member() {
    }

    public Member(long id, String name, String surname, String middleName, String email, String phone, String dayJoined, ESNcard esnCard, SIMCard simCard, long id1, String section, String role, boolean welcomePack) {
        super(id, name, surname, middleName, email, phone, dayJoined, esnCard, simCard, welcomePack);
        this.id = id1;
        this.section = section;
        Role = role;
    }
}
