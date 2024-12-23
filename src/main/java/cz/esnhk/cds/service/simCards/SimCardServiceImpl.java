package cz.esnhk.cds.service.simCards;

import cz.esnhk.cds.model.cards.CardStatusType;
import cz.esnhk.cds.model.cards.SIMCard;
import cz.esnhk.cds.repository.SimCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimCardServiceImpl implements SimCardService {
    SimCardRepository simCardRepository;

    @Autowired
    public SimCardServiceImpl(SimCardRepository simCardRepository) {
        this.simCardRepository = simCardRepository;
    }

    @Override
    public List<SIMCard> getAllSimCards() {
        return simCardRepository.findAll();
    }

    @Override
    public List<SIMCard> getAvailableSimCards() {
        return simCardRepository.findByCardStatus(CardStatusType.AVAILABLE);
    }

    @Override
    public SIMCard getSimCardByCardNumber(String cardNumber) {
        return simCardRepository.findBySimCardNumber(cardNumber);
    }

    @Override
    public SIMCard getSimCardById(long id) {
        return simCardRepository.findById(id).orElse(null);
    }

    @Override
    public void addSimCard(SIMCard esnCard) {
        simCardRepository.save(esnCard);
    }

    @Override
    public void editSimCard(long id, SIMCard esnCard) {
        //TODO
    }

    @Override
    public void issueSimCard(long id) {
        //TODO
    }

    @Override
    public List<SIMCard> getSimCardsByDateOfImport(String date) {
        return simCardRepository.findByDateOfImport(date);
    }

    @Override
    public List<SIMCard> getSimCardsByDateOfIssue(String date) {
        return simCardRepository.findByDateOfIssue(date);
    }

    @Override
    public List<SIMCard> getSimCardsByDateOfIssueMembers(String date) {
        return simCardRepository.countSIMCardsForMembersByDate(date);
    }

    @Override
    public List<SIMCard> getSimCardsByDateOfIssueInternationalStudents(String date) {
        return simCardRepository.countSIMCardsForInternationalStudentsByDate(date);
    }
}
