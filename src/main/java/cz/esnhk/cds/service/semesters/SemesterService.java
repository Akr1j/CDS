package cz.esnhk.cds.service.semesters;

import cz.esnhk.cds.model.Semester;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SemesterService {
    List<Semester> getAllSemesters();


    void addSemester(Semester semester);

    Semester getCurrentSemester();

    Semester getSemesterById(int id);
}
