package cz.esnhk.cds.service;

import cz.esnhk.cds.model.users.InternationalStudent;
import cz.esnhk.cds.model.users.InternationalStudentsDetailsResponse;
import cz.esnhk.cds.model.users.Member;
import cz.esnhk.cds.security.model.artemis_responses.UserDetailsResponse;
import cz.esnhk.cds.service.InternationalStudents.InternationalStudentService;
import cz.esnhk.cds.service.Members.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class ArtemisSynchronization {

    private final RestTemplate restTemplate;
    private final MemberService memberService;
    private final InternationalStudentService internationalStudentService;
    @Value("${members.url.artemis}")
    private String membersApiUrl;
    @Value("${international-students.url.artemis}")
    private String InternationalStudentsApiUrl;

    public ArtemisSynchronization(RestTemplate restTemplate, MemberService memberService, InternationalStudentService internationalStudentService) {
        this.restTemplate = restTemplate;
        this.memberService = memberService;
        this.internationalStudentService = internationalStudentService;
    }

    public void synchronizeAll(String token) {
        synchronizeMembers(token);
        synchronizeInternationalStudents(token);
    }

    public void synchronizeMembers(String token) {
        insertMembersIntoDatabase(getMembersList(token));
    }

    private List<UserDetailsResponse> getMembersList(String token) {
        //Prepare the request
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token); // Include the token in the header
        HttpEntity<Void> request = new HttpEntity<>(headers);

        //TODO: Change the response type to users immediately
        //Send the request and get the response
        ResponseEntity<UserDetailsResponse[]> response = restTemplate.exchange(membersApiUrl, HttpMethod.GET, request, UserDetailsResponse[].class);

        List<UserDetailsResponse> users = List.of(Objects.requireNonNull(response.getBody()));

        return users;
    }

    private void insertMembersIntoDatabase(List<UserDetailsResponse> users) {
        for (UserDetailsResponse user : users) {
            Member member = new Member();

            //TODO: Decide if using artemis or manual id
            member.setId(user.getId());

            member.setName(user.getFirst_name());
            member.setSurname(user.getLast_name());
            //member.setMiddleName(user.getMiddleName()); //Artemis does not have a middle name field
            member.setEmail(user.getEmail());
            member.setPhone(user.getNumber());
            member.setDayJoined(user.getDate_joined().toString());
            //TODO
            member.setSection("HK"); //Artemis does not have a section field do from properties files
            member.setFaculty(String.valueOf(user.getFaculty()));
            //TODO
            member.setRole("");
            member.setAboutMe(user.getDescription());

            memberService.addMember(member);
        }
    }

    public void synchronizeInternationalStudents(String token) {
        insertInternationalStudentsIntoDatabase(getInternationalStudentsList(token));
    }

    private List<InternationalStudentsDetailsResponse> getInternationalStudentsList(String token) {
        //Prepare the request
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token); // Include the token in the header
        HttpEntity<Void> request = new HttpEntity<>(headers);

        //TODO: Change the response type to users immediately
        //Send the request and get the response
        ResponseEntity<InternationalStudentsDetailsResponse[]> response = restTemplate.exchange(InternationalStudentsApiUrl, HttpMethod.GET, request, InternationalStudentsDetailsResponse[].class);

        List<InternationalStudentsDetailsResponse> users = List.of(Objects.requireNonNull(response.getBody()));

        return users;
    }

    private void insertInternationalStudentsIntoDatabase(List<InternationalStudentsDetailsResponse> users) {
        for (InternationalStudentsDetailsResponse user : users) {
            InternationalStudent internationalStudent = new InternationalStudent();

            internationalStudent.setId(user.getId());
            internationalStudent.setName(user.getFirst_name());
            internationalStudent.setSurname(user.getLast_name());
            internationalStudent.setDateOfBirth(user.getDateOfBirth());
            internationalStudent.setEmail(user.getEmail());
            internationalStudent.setPhone(user.getNumber());
            internationalStudent.setDayJoined(user.getDateJoined());
            internationalStudent.setFaculty(String.valueOf(user.getFaculty()));
            internationalStudent.setCountry(user.getCountry());
            internationalStudent.setHomeUniversity(user.getHomeUniversity());
            internationalStudent.setAboutMe(user.getDescription());

            internationalStudentService.addInternationalStudent(internationalStudent);
        }
    }

}
