package cz.esnhk.cds.service.semesters;

import cz.esnhk.cds.model.Semester;
import cz.esnhk.cds.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterImpl implements SemesterService {
    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterImpl(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @Override
    public void addSemester(Semester semester) {
        semesterRepository.save(semester);
    }

    @Override
    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    @Override
    public Semester getCurrentSemester() {
        return semesterRepository.findByCurrentTrue();
    }

    @Override
    public Semester getSemesterById(int id) {
        return semesterRepository.findById((long) id).orElse(null);
    }
}
