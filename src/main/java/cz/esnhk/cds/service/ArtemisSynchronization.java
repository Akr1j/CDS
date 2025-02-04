package cz.esnhk.cds.service;

import cz.esnhk.cds.model.Semester;
import cz.esnhk.cds.model.users.InternationalStudent;
import cz.esnhk.cds.model.users.InternationalStudentsDetailsResponse;
import cz.esnhk.cds.model.users.Member;
import cz.esnhk.cds.security.model.artemis_responses.UserDetailsResponse;
import cz.esnhk.cds.service.InternationalStudents.InternationalStudentService;
import cz.esnhk.cds.service.Members.MemberService;
import cz.esnhk.cds.service.semesters.SemesterService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ArtemisSynchronization {

    private final RestTemplate restTemplate;
    private final MemberService memberService;
    private final InternationalStudentService internationalStudentService;
    private final SemesterService semesterService;
    @Value("${members.url.artemis}")
    private String membersApiUrl;
    @Value("${international-students.url.artemis}")
    private String InternationalStudentsApiUrl;
    @Value("${semester.url.artemis}")
    private String semestersApiUrl;

    public ArtemisSynchronization(RestTemplate restTemplate, MemberService memberService, InternationalStudentService internationalStudentService, SemesterService semesterService) {
        this.restTemplate = restTemplate;
        this.memberService = memberService;
        this.internationalStudentService = internationalStudentService;
        this.semesterService = semesterService;
    }

    public void synchronizeAll(String token) {
        synchronizeSemesters(token);

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

            List<Integer> userSemesters = new ArrayList<>();
            for (int semester : user.getSemesters()) {
                userSemesters.add(semester);
            }

            List<Semester> semesters = new ArrayList<>();
            for (Semester semester : semesterService.getAllSemesters()) {
                if (userSemesters.contains(semester.getId())) {
                    semesters.add(semester);
                }

            }
            internationalStudent.setSemesters(semesters);

            internationalStudentService.addInternationalStudent(internationalStudent);
        }
    }

    private void synchronizeSemesters(String token) {
        //Prepare the request
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token); // Include the token in the header
        HttpEntity<Void> request = new HttpEntity<>(headers);

        //TODO: Change the response type to users immediately
        //Send the request and get the response
        ResponseEntity<SemesterResponse[]> response = restTemplate.exchange(semestersApiUrl, HttpMethod.GET, request, SemesterResponse[].class);

        List<SemesterResponse> semesterList = List.of(Objects.requireNonNull(response.getBody()));

        for (SemesterResponse semesterResponse : semesterList) {
            Semester semester = new Semester();
            semester.setId((int) semesterResponse.getId());
            semester.setName(semesterResponse.getLabel());
            semester.setCurrent(semesterResponse.is_current.equals("true"));
            semester.setAcademicYear(semesterResponse.getYear());
            semester.setSeason(semesterResponse.getSemester());

            semesterService.addSemester(semester);
        }
    }

    @Data
    @NoArgsConstructor
    private static class SemesterResponse {
        long id;
        //String semester_info;
        String is_current;
        String label;
        int year;
        String semester;
    }

}
