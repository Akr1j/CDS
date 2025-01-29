package cz.esnhk.cds.security;

import cz.esnhk.cds.security.model.artemis_responses.UserDetailsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationService {
    private final RestTemplate restTemplate;
    @Value("${members.group-allowed}")
    private String group_allowed;
    @Value("${members.url.artemis}")
    private String membersApiUrl;

    public AuthorizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Authorize the user based on the groups he is in
     *
     * @param userId The ID of the user
     * @param role   The role of the user
     * @return True if the user is authorized, false otherwise
     */
    public boolean authorize(int userId, String role) {
        //TODO: Group is not always the first element in the array check all elements
        String groups = String.valueOf(getUserDetails(userId, role).getGroups()[0]);
        return groups.equals(group_allowed);
    }

    /**
     * Get the user details from the members API (Artemis)
     *
     * @param userId The ID of the user
     * @param token  The token to authenticate the request
     * @return The user details
     */
    @Cacheable("userDetails")
    public UserDetailsResponse getUserDetails(int userId, String token) {
        String url = membersApiUrl + "/" + userId + "/";

        //Prepare the request
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token); // Include the token in the header
        HttpEntity<Void> request = new HttpEntity<>(headers);

        //Send the request and get the response
        ResponseEntity<UserDetailsResponse> response = restTemplate.exchange(url, HttpMethod.GET, request, UserDetailsResponse.class);

        return response.getBody();
    }
}
