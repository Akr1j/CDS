package cz.esnhk.cds.security;

import cz.esnhk.cds.security.model.artemis_responses.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private final RestTemplate restTemplate;
    @Value("${login.url.artemis}")
    private String artemisUrl;

    @Autowired
    public AuthenticationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Authenticate the user with the given email and password against the Artemis authentication server.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return The response from the authentication server.
     */
    public AuthResponse authenticate(String email, String password) {
        // Prepare headers (optional, e.g., for Content-Type)
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        // Prepare the request body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);

        // Wrap in HttpEntity
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        // Send POST request
        try {
            ResponseEntity<AuthResponse> response = restTemplate.exchange(artemisUrl, HttpMethod.POST, request, AuthResponse.class);
            return response.getBody();
        } catch (HttpClientErrorException.Unauthorized e) {
            // Handle 401 Unauthorized error
            System.out.println("Unauthorized request: " + e.getMessage());
            throw new RuntimeException("Unauthorized access. Please check your credentials.");
        } catch (Exception e) {
            // Handle other exceptions
            System.out.println("Error occurred: " + e.getMessage());
            throw new RuntimeException("An error occurred while authenticating.");
        }
    }
}
