package cz.esnhk.cds.service.InternationalStudents;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import cz.esnhk.cds.model.users.InternationalStudent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InternationalStudentService {
    List<InternationalStudent> getAllInternationalStudents();

    InternationalStudent getInternationalStudentById(long id);

    void addInternationalStudent(InternationalStudent internationalStudent);

    void addESNcard(long id, ESNcard cardNumber);

    void assignSimCard(long id, SIMCard simCard);
}
