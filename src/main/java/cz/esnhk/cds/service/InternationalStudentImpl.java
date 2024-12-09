package cz.esnhk.cds.service;

import cz.esnhk.cds.model.users.InternationalStudent;
import cz.esnhk.cds.repository.InternationalStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternationalStudentImpl implements InternationalStudentService {
    InternationalStudentRepository internationalStudentRepository;

    @Autowired
    public InternationalStudentImpl(InternationalStudentRepository internationalStudentRepository) {
        this.internationalStudentRepository = internationalStudentRepository;
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
}
