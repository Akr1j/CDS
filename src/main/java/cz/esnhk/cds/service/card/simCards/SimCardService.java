package cz.esnhk.cds.service.card.simCards;

import cz.esnhk.cds.model.cards.SIMCard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SimCardService {
    List<SIMCard> getAllSimCards();

    List<SIMCard> getAvailableSimCards();

    SIMCard getSimCardByCardNumber(String cardNumber);

    SIMCard getSimCardById(long id);

    void addSimCard(SIMCard simCard);

    void editSimCard(long id, SIMCard simCard);

    void issueSimCard(long id);

    List<SIMCard> getSimCardsByDateOfImport(String date);

    List<SIMCard> getSimCardsByDateOfIssue(String date);

    List<SIMCard> getSimCardsByDateOfIssueInternationalStudents(String date);

    List<SIMCard> getSimCardsByDateOfIssueMembers(String date);
}
