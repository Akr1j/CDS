package cz.esnhk.cds.model.users;

import jakarta.persistence.*;

@Entity
@Table(name = "international_students")
public class InternationalStudent extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String country;


}
