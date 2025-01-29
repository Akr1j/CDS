package cz.esnhk.cds;

import cz.esnhk.cds.model.cards.CardStatusType;
import cz.esnhk.cds.model.cards.ESNcard;
import cz.esnhk.cds.model.cards.SIMCard;
import cz.esnhk.cds.model.users.InternationalStudent;
import cz.esnhk.cds.model.users.Member;
import cz.esnhk.cds.repository.ESNCardRepository;
import cz.esnhk.cds.service.InternationalStudents.InternationalStudentService;
import cz.esnhk.cds.service.Members.MemberService;
import cz.esnhk.cds.service.card.esnCards.EsnCardService;
import cz.esnhk.cds.service.card.simCards.SimCardService;
import jakarta.transaction.Transactional;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DummyData {

    private final EsnCardService esnCardService;
    private final MemberService memberService;
    private final ESNCardRepository esnCardRepository;
    private final InternationalStudentService internationalStudentsService;
    private final SimCardService simCardService;

    public DummyData(EsnCardService esnCardService, MemberService memberService, ESNCardRepository esnCardRepository, InternationalStudentService internationalStudentService, SimCardService simCardService) {
        this.esnCardService = esnCardService;
        this.memberService = memberService;
        this.esnCardRepository = esnCardRepository;
        this.internationalStudentsService = internationalStudentService;
        this.simCardService = simCardService;
    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void insertDummyData() {
        System.out.println("Inserting dummy data");
        insertInternationalStudents();
        insertMembers();
        insertESNCards();
        insertSIMCards();

        assignESNCardToInternationalStudents();
        assignESNCardToMembers();
        assignSIMCardToInternationalStudents();
        assignSIMCardToMembers();
    }


    private void insertInternationalStudents() {
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

        internationalStudentsService.addInternationalStudent(internationalStudent);

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

        internationalStudentsService.addInternationalStudent(internationalStudent2);

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

        internationalStudentsService.addInternationalStudent(internationalStudent3);
    }

    private void insertMembers() {
        Member member = new Member();
        member.setName("Tomáš");
        member.setSurname("Pech");
        member.setMiddleName("");
        member.setEmail("chodi@okolo.cz");
        member.setPhone("+420722699006");
        member.setDayJoined("12/12/2024");
        member.setWelcomePack(false);
        member.setAboutMe("Hello, I am Tomáš");

        memberService.addMember(member);

        Member member2 = new Member();
        member2.setName("Petr");
        member2.setSurname("Molík");
        member2.setMiddleName("");
        member2.setEmail("molik@seznam.cz");
        member2.setPhone("+420722699006");
        member2.setDayJoined("12/12/2024");
        member2.setWelcomePack(false);
        member2.setAboutMe("Hello, I am Petr");

        memberService.addMember(member2);

        Member member3 = new Member();
        member3.setName("Simona");
        member3.setSurname("Hanzlíková");
        member3.setMiddleName("");
        member3.setEmail("sisi@gmial.com");
        member3.setPhone("+420722699006");
        member3.setDayJoined("12/12/2024");
        member3.setWelcomePack(false);
        member3.setAboutMe("Hello, I am Simona");

        memberService.addMember(member3);
    }

    private void insertESNCards() {
        ESNcard esNcard = new ESNcard("123456789", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard);

        ESNcard esNcard2 = new ESNcard("987654321", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard2);

        ESNcard esNcard3 = new ESNcard("123123123", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard3);

        ESNcard esNcard4 = new ESNcard("321321321", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard4);

        ESNcard esNcard5 = new ESNcard("456456456", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard5);

        ESNcard esNcard6 = new ESNcard("654654654", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard6);

        ESNcard esNcard7 = new ESNcard("789789789", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard7);

        ESNcard esNcard8 = new ESNcard("987987987", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard8);

        ESNcard esNcard9 = new ESNcard("654654654", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard9);

        ESNcard esNcard10 = new ESNcard("123123123", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard10);

        ESNcard esNcard11 = new ESNcard("321321321", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard11);

        ESNcard esNcard12 = new ESNcard("456456456", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard12);

        ESNcard esNcard13 = new ESNcard("654654654", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard13);

        ESNcard esNcard14 = new ESNcard("789789789", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard14);

        ESNcard esNcard15 = new ESNcard("987987987", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard15);

        ESNcard esNcard16 = new ESNcard("654654654", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard16);

        ESNcard esNcard17 = new ESNcard("123123123", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard17);

        ESNcard esNcard18 = new ESNcard("321321321", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard18);

        ESNcard esNcard19 = new ESNcard("456456456", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard19);

        ESNcard esNcard20 = new ESNcard("654654654", "12/12/2024", null, 100);
        esnCardRepository.save(esNcard20);
    }

    private void insertSIMCards() {
        SIMCard simCard = new SIMCard("123456789", "12/12/2024", null, CardStatusType.AVAILABLE);
        simCardService.addSimCard(simCard);

        SIMCard simCard2 = new SIMCard("987654321", "12/12/2024", null, CardStatusType.AVAILABLE);
        simCardService.addSimCard(simCard2);

        SIMCard simCard3 = new SIMCard("123123123", "12/12/2024", null, CardStatusType.AVAILABLE);
        simCardService.addSimCard(simCard3);

        SIMCard simCard4 = new SIMCard("321321321", "12/12/2024", null, CardStatusType.AVAILABLE);
        simCardService.addSimCard(simCard4);

        SIMCard simCard5 = new SIMCard("456456456", "12/12/2024", null, CardStatusType.AVAILABLE);
        simCardService.addSimCard(simCard5);

        SIMCard simCard6 = new SIMCard("654654654", "12/12/2024", null, CardStatusType.AVAILABLE);
        simCardService.addSimCard(simCard6);

        SIMCard simCard7 = new SIMCard("789789789", "12/12/2024", null, CardStatusType.AVAILABLE);
        simCardService.addSimCard(simCard7);

        SIMCard simCard8 = new SIMCard("987987987", "12/12/2024", null, CardStatusType.AVAILABLE);
        simCardService.addSimCard(simCard8);

        SIMCard simCard9 = new SIMCard("654654654", "12/12/2024", null, CardStatusType.AVAILABLE);
        simCardService.addSimCard(simCard9);

        SIMCard simCard10 = new SIMCard("123123123", "12/12/2024", null, CardStatusType.AVAILABLE);
        simCardService.addSimCard(simCard10);

        SIMCard simCard11 = new SIMCard("321321321", "12/12/2024", null, CardStatusType.AVAILABLE);
        simCardService.addSimCard(simCard11);

        SIMCard simCard12 = new SIMCard("456456456", "12/12/2024", null, CardStatusType.AVAILABLE);
        simCardService.addSimCard(simCard12);
    }

    private void assignESNCardToInternationalStudents() {
        ESNcard esnCard = esnCardService.getEsnCardById(1);
        esnCard.setDateOfIssue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        esnCard.setCardStatus(CardStatusType.ISSUED);

        internationalStudentsService.addESNcard(1, esnCard);


        ESNcard esnCard2 = esnCardService.getEsnCardById(2);
        esnCard2.setDateOfIssue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        esnCard2.setCardStatus(CardStatusType.ISSUED);

        internationalStudentsService.addESNcard(3, esnCard2);
    }

    private void assignESNCardToMembers() {
        ESNcard esnCard = esnCardService.getEsnCardById(4);
        esnCard.setDateOfIssue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        esnCard.setCardStatus(CardStatusType.ISSUED);
        memberService.addESNcard(1, esnCard);

        ESNcard esnCard2 = esnCardService.getEsnCardById(5);
        esnCard2.setDateOfIssue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        esnCard2.setCardStatus(CardStatusType.ISSUED);
        memberService.addESNcard(2, esnCard2);

        ESNcard esnCard3 = esnCardService.getEsnCardById(6);
        esnCard3.setDateOfIssue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        esnCard3.setCardStatus(CardStatusType.ISSUED);
        memberService.addESNcard(3, esnCard3);
    }

    private void assignSIMCardToInternationalStudents() {
        SIMCard simCard = simCardService.getSimCardById(1);
        simCard.setDateOfIssue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        simCard.setCardStatus(CardStatusType.ISSUED);

        internationalStudentsService.assignSimCard(1, simCard);

        SIMCard simCard2 = simCardService.getSimCardById(2);
        simCard2.setDateOfIssue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        simCard2.setCardStatus(CardStatusType.ISSUED);

        internationalStudentsService.assignSimCard(3, simCard2);
    }

    private void assignSIMCardToMembers() {
        SIMCard simCard = simCardService.getSimCardById(3);
        simCard.setDateOfIssue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        simCard.setCardStatus(CardStatusType.ISSUED);

        memberService.assignSIMCard(1, simCard);
    }

}
