package cz.esnhk.cds.repository;

import cz.esnhk.cds.model.cards.CardStatusType;
import cz.esnhk.cds.model.cards.ESNcard;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ESNCardRepository extends JpaRepository<ESNcard, Long> {
    List<ESNcard> findByCardStatus(CardStatusType cardStatus);

    ESNcard findByCardNumber(String cardNumber);

    List<ESNcard> findByDateOfImport(@NotBlank String dateOfImport);

    List<ESNcard> findByDateOfIssue(@NotBlank String dateOfIssue);

    @Query("SELECT COUNT(e) FROM InternationalStudent i JOIN i.esnCards e")
    Long countESNCardsForInternationalStudents();

    @Query("SELECT COUNT(e) FROM Member m JOIN m.esnCards e")
    Long countESNCardsForMembers();

    @Query("SELECT e FROM InternationalStudent i JOIN i.esnCards e WHERE e.dateOfIssue = :date")
    List<ESNcard> countESNCardsForInternationalStudentsByDate(@NotBlank String date);

    @Query("SELECT e FROM Member m JOIN m.esnCards e WHERE e.dateOfIssue = :date")
    List<ESNcard> countESNCardsForMembersByDate(@NotBlank String date);
}
