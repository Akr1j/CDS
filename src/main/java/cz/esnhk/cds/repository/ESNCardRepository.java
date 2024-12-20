package cz.esnhk.cds.repository;

import cz.esnhk.cds.model.cards.ESNcard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ESNCardRepository extends JpaRepository<ESNcard, Long> {
    ESNcard findByCardNumber(String cardNumber);
}
