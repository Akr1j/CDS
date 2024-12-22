package cz.esnhk.cds.service;

import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.users.InternationalStudent;
import cz.esnhk.cds.repository.ESNCardRepository;
import cz.esnhk.cds.repository.InternationalStudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
//Set database to initial state before each test
@Transactional
//Set up the Spring application context before each test
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class InternationalStudentImplTest {

    @Autowired
    private InternationalStudentImpl internationalStudentImpl;

    @Autowired
    private InternationalStudentRepository internationalStudentRepository;

    @Autowired
    private ESNCardRepository ESNCardRepository;

    @BeforeEach
    public void init() {
        System.out.println("Setting up test");
        internationalStudentRepository.deleteAll();
        ESNCardRepository.deleteAll();

        InternationalStudent internationalStudent = new InternationalStudent();
        internationalStudent.setName("John");
        internationalStudent.setSurname("Doe");
        internationalStudent.setMiddleName("");
        internationalStudent.setEmail("john@dee.com");
        internationalStudent.setPhone("722699006");
        internationalStudent.setDayJoined("12/12/2024");
        internationalStudent.setWelcomePack(false);
        internationalStudent.setCountry("USA");
        internationalStudent.setAboutMe("I am John Doe");
        internationalStudentRepository.save(internationalStudent);

        InternationalStudent internationalStudent2 = new InternationalStudent();
        internationalStudent2.setName("Jan");
        internationalStudent2.setSurname("Zvoník");
        internationalStudent2.setMiddleName("");
        internationalStudent2.setEmail("zvonik@gmail.com");
        internationalStudent2.setPhone("+420980745678");
        internationalStudent2.setDayJoined("20/12/2024");
        internationalStudent2.setWelcomePack(true);
        internationalStudent2.setCountry("Czech Republic");
        internationalStudent2.setAboutMe("Hello there");

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

        internationalStudentRepository.flush();
        System.out.println("Test setup done");
    }

    @Test
    public void testAddInternationalStudent() {
        InternationalStudent internationalStudent = new InternationalStudent();
        internationalStudent.setName("John");
        internationalStudent.setSurname("Doe");
        internationalStudent.setMiddleName("");
        internationalStudent.setEmail("john@dee.com");
        internationalStudent.setPhone("722699006");
        internationalStudent.setDayJoined("12/12/2024");
        internationalStudent.setWelcomePack(false);
        internationalStudent.setCountry("USA");
        internationalStudent.setAboutMe("I am John Doe");

        System.out.println(internationalStudent);
        internationalStudentImpl.addInternationalStudent(internationalStudent);

        // Assert: Check that the student was added successfully
        List<InternationalStudent> students = internationalStudentRepository.findAll();
        assertThat(students.size()).isEqualTo(4);
        assertThat(students.get(0).getName()).isEqualTo("John");
    }

    @Test
    public void testGetAllInternationalStudents() {
        List<InternationalStudent> students = internationalStudentImpl.getAllInternationalStudents();

        assertThat(students.size()).isEqualTo(3);
        assertThat(students.get(0).getName()).isEqualTo("John");
        assertThat(students.get(1).getName()).isEqualTo("Jan");
        assertThat(students.get(2).getName()).isEqualTo("Petr");
    }

    @Test
    public void testGetInternationalStudentById() {
        InternationalStudent student = internationalStudentImpl.getInternationalStudentById(1);

        assertThat(student).isNotNull();
        assertThat(student.getName()).isEqualTo("John");
    }

    @Test
    public void testAddESNcard() {
        ESNcard esNcard = new ESNcard("123", "12/12/2024", "12/12/2024", 100);

        internationalStudentImpl.addESNcard(1, esNcard);

        InternationalStudent student = internationalStudentImpl.getInternationalStudentById(1);
        assertThat(student.getEsnCards().size()).isEqualTo(1);
        assertThat(student.getEsnCards().get(0).getCardNumber()).isEqualTo("123");
    }
}
