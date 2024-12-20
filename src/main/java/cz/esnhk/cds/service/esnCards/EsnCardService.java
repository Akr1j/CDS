package cz.esnhk.cds.service.esnCards;

import cz.esnhk.cds.model.cards.ESNcard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EsnCardService {
    List<ESNcard> getAllEsnCards();
    ESNcard getEsnCardByCardNumber(String cardNumber);
    void addEsnCard(ESNcard esnCard);
    void editEsnCard(long id, ESNcard esnCard);
    void issueEsnCard(long id);
}
