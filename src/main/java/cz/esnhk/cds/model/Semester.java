package cz.esnhk.cds.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "semesters")
@Data
public class Semester {
    @Id
    private int id;
    private String name;
    private boolean current;

    private int academicYear;
    private String season;
}
