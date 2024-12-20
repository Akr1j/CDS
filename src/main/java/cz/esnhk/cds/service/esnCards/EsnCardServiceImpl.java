package cz.esnhk.cds.service.esnCards;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.repository.ESNCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsnCardServiceImpl implements EsnCardService {
    ESNCardRepository esNcardRepository;

    @Autowired
    public EsnCardServiceImpl(ESNCardRepository esNcardRepository) {
        this.esNcardRepository = esNcardRepository;
    }

    @Override
    public List<ESNcard> getAllEsnCards() {
        return esNcardRepository.findAll();
    }

    @Override
    public ESNcard getEsnCardByCardNumber(String cardNumber) {
        return esNcardRepository.findByCardNumber(cardNumber);
    }

    @Override
    public void addEsnCard(ESNcard esnCard) {
        esNcardRepository.save(esnCard);
    }

    @Override
    public void editEsnCard(long id, ESNcard esnCard) {
        //TODO
    }

    @Override
    public void issueEsnCard(long id) {
        //TODO
    }
}
