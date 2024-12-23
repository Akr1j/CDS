package cz.esnhk.cds.service.InternationalStudents;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import cz.esnhk.cds.model.users.InternationalStudent;
import cz.esnhk.cds.repository.ESNCardRepository;
import cz.esnhk.cds.repository.InternationalStudentRepository;
import cz.esnhk.cds.repository.SimCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternationalStudentImpl implements InternationalStudentService {
    private final SimCardRepository simCardRepository;
    InternationalStudentRepository internationalStudentRepository;
    ESNCardRepository ESNCardRepository;

    @Autowired
    public InternationalStudentImpl(InternationalStudentRepository internationalStudentRepository, ESNCardRepository ESNCardRepository, SimCardRepository simCardRepository) {
        this.internationalStudentRepository = internationalStudentRepository;
        this.ESNCardRepository = ESNCardRepository;
        this.simCardRepository = simCardRepository;
    }

    @Override
    public List<InternationalStudent> getAllInternationalStudents() {
        return internationalStudentRepository.findAll();
    }

    @Override
    public InternationalStudent getInternationalStudentById(long id) {
        return internationalStudentRepository.findById(id).orElse(null);
    }

    @Override
    public void addInternationalStudent(InternationalStudent internationalStudent) {
        internationalStudentRepository.save(internationalStudent);
    }

    @Override
    public void addESNcard(long id, ESNcard esnCard) {
        //REFACTOR: cards already taken from database IDK if this is necessary
        InternationalStudent internationalStudent = internationalStudentRepository.findById(id).orElse(null);
        ESNcard esnCard1 = ESNCardRepository.findById(esnCard.getId()).orElse(null);
        if (internationalStudent != null && esnCard1 != null) {
            internationalStudent.addESNcard(esnCard1);
            ESNCardRepository.save(esnCard1);
            internationalStudentRepository.save(internationalStudent);
        }
    }

    @Override
    public void assignSimCard(long internationalStudentId, SIMCard simCard) {
        InternationalStudent internationalStudent = internationalStudentRepository.findById(internationalStudentId).orElse(null);
        SIMCard simCard1 = simCardRepository.findById(simCard.getId()).orElse(null);
        if (internationalStudent != null && simCard1 != null) {
            internationalStudent.addSimCard(simCard1);
            simCardRepository.save(simCard1);
            internationalStudentRepository.save(internationalStudent);
        }
    }
}
