package cz.esnhk.cds.service;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.users.InternationalStudent;
import cz.esnhk.cds.repository.ESNCardRepository;
import cz.esnhk.cds.repository.InternationalStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternationalStudentImpl implements InternationalStudentService {
    InternationalStudentRepository internationalStudentRepository;
    ESNCardRepository ESNCardRepository;

    @Autowired
    public InternationalStudentImpl(InternationalStudentRepository internationalStudentRepository, ESNCardRepository ESNCardRepository) {
        this.internationalStudentRepository = internationalStudentRepository;
        this.ESNCardRepository = ESNCardRepository;
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
        InternationalStudent internationalStudent = internationalStudentRepository.findById(id).orElse(null);
        ESNcard esNcard1 = new ESNcard(esnCard.getCardNumber(), esnCard.getDateOfImport(), esnCard.getDateOfIssue(), esnCard.getCardPrice());
        if (internationalStudent != null) {
            ESNCardRepository.save(esNcard1);
            internationalStudent.addESNcard(esNcard1);
            internationalStudentRepository.save(internationalStudent);
        }
        internationalStudent = internationalStudentRepository.findById(id).orElse(null);
        System.out.println("International student: " + internationalStudent);
    }
}
