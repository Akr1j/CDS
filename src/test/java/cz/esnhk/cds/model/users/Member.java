package cz.esnhk.cds.model.users;

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
}
