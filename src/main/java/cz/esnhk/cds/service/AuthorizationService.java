package cz.esnhk.cds.service;

import cz.esnhk.cds.model.UserDetailsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationService {
    @Value("${members.group-allowed}")
    private String group_allowed;

    public boolean authorize(int userId, String role) {

        //TODO dynamic group
        String groups = String.valueOf(getUserDetails(userId, role).getGroups()[0]);

        //int group_allowed = Integer.parseInt(this.group_allowed);

        return groups.equals(group_allowed);
    }


    private final RestTemplate restTemplate;

    @Value("${members.url.artemis}") // E.g., "https://artemis.esnhk.cz/api/v1/users"
    private String membersApiUrl;

    public AuthorizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDetailsResponse getUserDetails(int userId, String token) {
        String url = membersApiUrl + "/" + userId + "/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token); // Include the token in the header

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<UserDetailsResponse> response = restTemplate.exchange(url, HttpMethod.GET, request, UserDetailsResponse.class);

        return response.getBody(); // List of roles
    }
}
