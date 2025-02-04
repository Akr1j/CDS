package cz.esnhk.cds.repository;

import cz.esnhk.cds.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
}
