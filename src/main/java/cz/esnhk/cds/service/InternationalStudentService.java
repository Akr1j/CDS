package cz.esnhk.cds.service;

import cz.esnhk.cds.model.users.InternationalStudent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InternationalStudentService {
    List<InternationalStudent> getAllInternationalStudents();
    InternationalStudent getInternationalStudentById(long id);
    void addInternationalStudent(InternationalStudent internationalStudent);
}
