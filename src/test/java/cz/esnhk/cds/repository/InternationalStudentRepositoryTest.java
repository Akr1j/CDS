package cz.esnhk.cds.repository;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import cz.esnhk.cds.model.users.InternationalStudent;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
//Set database to initial state before each test
@Transactional
//Set up the Spring application context before each test
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class InternationalStudentRepositoryTest {

    @Autowired
    private InternationalStudentRepository internationalStudentRepository;

    @Autowired
    private ESNCardRepository esnCardRepository;

    @Test
    public void databaseConnectionTest() {
        assertThat(internationalStudentRepository).isNotNull();
        assertThat(esnCardRepository).isNotNull();
    }


    @BeforeEach
    public void init() {
        //TODO: Add SIMCard
        internationalStudentRepository.deleteAll();
        esnCardRepository.deleteAll();

        InternationalStudent internationalStudent = new InternationalStudent();
        internationalStudent.setName("John");
        internationalStudent.setSurname("Doe");
        internationalStudent.setMiddleName("");
        internationalStudent.setEmail("john@dee.com");
        internationalStudent.setPhone("722699006");
        internationalStudent.setDayJoined("12/12/2024");
        List<ESNcard> esnCards = new ArrayList<>();
        esnCards.add(new ESNcard("123456", "12/12/2024", "12/12/2024", 100));
        internationalStudent.setEsnCards(esnCards);
        internationalStudent.setWelcomePack(false);
        internationalStudent.setCountry("USA");
        internationalStudent.setAboutMe("I am John Doe");

        esnCardRepository.save(esnCards.get(0));
        internationalStudentRepository.save(internationalStudent);

        InternationalStudent internationalStudent2 = new InternationalStudent();
        internationalStudent2.setName("Jan");
        internationalStudent2.setSurname("Zvoník");
        internationalStudent2.setMiddleName("");
        internationalStudent2.setEmail("zvonik@gmail.com");
        internationalStudent2.setPhone("+420980745678");
        internationalStudent2.setDayJoined("20/12/2024");
        List<ESNcard> esnCards2 = new ArrayList<>();
        esnCards2.add(new ESNcard("654321", "20/12/2024", "20/12/2024", 200));
        internationalStudent2.setEsnCards(esnCards2);
        internationalStudent2.setWelcomePack(true);
        internationalStudent2.setCountry("Czech Republic");
        internationalStudent2.setAboutMe("Hello there");

        esnCardRepository.save(esnCards2.get(0));
        internationalStudentRepository.save(internationalStudent2);

        // No ESNcard and SIMCard
        InternationalStudent internationalStudent3 = new InternationalStudent();
        internationalStudent3.setName("Petr");
        internationalStudent3.setSurname("Novák");
        internationalStudent3.setMiddleName("");
        internationalStudent3.setEmail("novak@novakovicz.cz");
        internationalStudent3.setPhone("+420123456789");
        internationalStudent3.setDayJoined("20/12/2024");
        internationalStudent3.setWelcomePack(true);
        internationalStudent3.setCountry("Czech Republic");
        internationalStudent3.setAboutMe("General Keno-bi");

        internationalStudentRepository.save(internationalStudent3);
    }

    @Test
    public void databaseInsertTest() {
        InternationalStudent internationalStudent = new InternationalStudent();
        internationalStudent.setName("John");
        internationalStudent.setSurname("Doe");
        internationalStudent.setMiddleName("");
        internationalStudent.setEmail("john@dee.com");
        internationalStudent.setPhone("722699006");
        internationalStudent.setDayJoined("12/12/2024");
        List<ESNcard> esnCards = new ArrayList<>();
        esnCards.add(new ESNcard());
        internationalStudent.setEsnCards(esnCards);
        List<SIMCard> simCards = new ArrayList<>();
        simCards.add(new SIMCard());
        internationalStudent.setSimCards(simCards);
        internationalStudent.setWelcomePack(false);
        internationalStudent.setCountry("USA");
        internationalStudent.setAboutMe("I am John Doe");

        InternationalStudent insertedStudent = internationalStudentRepository.save(internationalStudent);

        Assertions.assertEquals(internationalStudent.getId(), insertedStudent.getId());
        Assertions.assertEquals("John", insertedStudent.getName());
        Assertions.assertEquals("Doe", insertedStudent.getSurname());
        Assertions.assertEquals("", insertedStudent.getMiddleName());
        Assertions.assertEquals("john@dee.com", insertedStudent.getEmail());
        Assertions.assertEquals("722699006", insertedStudent.getPhone());
        Assertions.assertEquals("12/12/2024", insertedStudent.getDayJoined());
        Assertions.assertEquals(internationalStudent.getEsnCards(), insertedStudent.getEsnCards());
        Assertions.assertEquals(internationalStudent.getSimCards(), insertedStudent.getSimCards());
        Assertions.assertEquals(false, insertedStudent.welcomePack());
        Assertions.assertEquals("USA", insertedStudent.getCountry());
        Assertions.assertEquals("I am John Doe", insertedStudent.getAboutMe());
    }

    @Test
    public void databaseFindAllTest() {
        assertThat(internationalStudentRepository.findAll().size()).isEqualTo(3);
        assertThat(internationalStudentRepository.findAll().get(0).getName()).isEqualTo("John");
        assertThat(internationalStudentRepository.findAll().get(1).getName()).isEqualTo("Jan");
        assertThat(internationalStudentRepository.findAll().get(2).getName()).isEqualTo("Petr");
    }
}
