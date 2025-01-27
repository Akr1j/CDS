package cz.esnhk.cds.repository;

import cz.esnhk.cds.model.cards.ESNcard;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
//Set database to initial state before each test
@Transactional
//Set up the Spring application context before each test
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ESNCardRepositoryTest {

    @Autowired
    private ESNCardRepository esnCardRepository;

    @BeforeEach
    public void init() {
        esnCardRepository.deleteAll();
    }

    @Test
    public void databaseConnectionTest() {
        assertThat(esnCardRepository).isNotNull();
    }

    @Test
    public void saveESNCardTest() {
        ESNcard esnCard = new ESNcard("123456", "12/12/2024", "12/12/2024", 100);
        esnCardRepository.save(esnCard);

        assertThat(esnCardRepository.findById(esnCard.getId())).isNotNull();
    }

    @Test
    public void deleteESNCardTest() {
        ESNcard esnCard = new ESNcard("123456", "12/12/2024", "12/12/2024", 100);
        esnCardRepository.save(esnCard);

        esnCardRepository.delete(esnCard);

        assertThat(esnCardRepository.findById(esnCard.getId())).isEmpty();
    }
}
