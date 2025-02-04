package cz.esnhk.cds.repository;

import cz.esnhk.cds.model.users.InternationalStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternationalStudentRepository extends JpaRepository<InternationalStudent, Long> {

    List<InternationalStudent> findBySemestersId(int semesterId);
}
