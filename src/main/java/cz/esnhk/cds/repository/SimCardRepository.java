package cz.esnhk.cds.repository;

import cz.esnhk.cds.model.cards.CardStatusType;
import cz.esnhk.cds.model.cards.SIMCard;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SimCardRepository extends JpaRepository<SIMCard, Long> {
    List<SIMCard> findByCardStatus(CardStatusType cardStatus);

    SIMCard findByCardNumber(String cardNumber);

    List<SIMCard> findByDateOfImport(@NotBlank String dateOfImport);

    List<SIMCard> findByDateOfIssue(@NotBlank String dateOfIssue);

    @Query("SELECT COUNT(e) FROM InternationalStudent i JOIN i.simCards e")
    Long countSIMCardsForInternationalStudents();

    @Query("SELECT COUNT(e) FROM Member m JOIN m.simCards e")
    Long countSIMCardsForMembers();

    @Query("SELECT e FROM InternationalStudent i JOIN i.simCards e WHERE e.dateOfIssue = :date")
    List<SIMCard> countSIMCardsForInternationalStudentsByDate(@NotBlank String date);

    @Query("SELECT e FROM Member m JOIN m.simCards e WHERE e.dateOfIssue = :date")
    List<SIMCard> countSIMCardsForMembersByDate(@NotBlank String date);
}
