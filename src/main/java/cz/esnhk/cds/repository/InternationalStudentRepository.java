package cz.esnhk.cds.repository;

import cz.esnhk.cds.model.users.InternationalStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternationalStudentRepository extends JpaRepository<InternationalStudent, Long> {
}
