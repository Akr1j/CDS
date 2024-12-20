package cz.esnhk.cds.service.esnCards;

import cz.esnhk.cds.model.cards.CardStatusType;
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
    public List<ESNcard> getAvailableEsnCards() {
        return esNcardRepository.findByCardStatus(CardStatusType.AVAILABLE);
    }

    @Override
    public ESNcard getEsnCardByCardNumber(String cardNumber) {
        return esNcardRepository.findByCardNumber(cardNumber);
    }

    @Override
    public ESNcard getEsnCardById(long id) {
        return esNcardRepository.findById(id).orElse(null);
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

    @Override
    public List<ESNcard> getEsnCardsByDateOfImport(String date) {
        return esNcardRepository.findByDateOfImport(date);
    }

    @Override
    public List<ESNcard> getEsnCardsByDateOfIssue(String date) {
        return esNcardRepository.findByDateOfIssue(date);
    }

    @Override
    public List<ESNcard> getESNCardsByDateOfIssueMembers(String date) {
        return esNcardRepository.countESNCardsForMembersByDate(date);
    }

    @Override
    public List<ESNcard> getESNCardsByDateOfIssueInternationalStudents(String date) {
        return esNcardRepository.countESNCardsForInternationalStudentsByDate(date);
    }
}
