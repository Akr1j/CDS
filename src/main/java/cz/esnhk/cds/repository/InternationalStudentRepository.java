package cz.esnhk.cds.repository;

import cz.esnhk.cds.model.users.InternationalStudent;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternationalStudentRepository extends JpaRepository<InternationalStudent, Long> {

    List<InternationalStudent> findBySemestersId(int semesterId);

    List<InternationalStudent> findBySemestersIdAndCountryContaining(int semestersId, String country);

    List<InternationalStudent> findBySemestersIdAndNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(int semestersId, @NotBlank String name, @NotBlank String surname);
}
