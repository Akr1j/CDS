package cz.esnhk.cds.security.filter;

import com.auth0.jwt.JWT;
import cz.esnhk.cds.security.AuthorizationService;
import cz.esnhk.cds.security.model.artemis_responses.UserDetailsResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CustomAuthenticationFilter extends OncePerRequestFilter {
    //TODO: Nebude se náhodou spouětět vždy i když už je užitatel přihlášený jelikož dědí z OncePerRequestFilter?

    public static final String TOKEN_COOKIE = "token";
    private final AuthorizationService authorizationService;

    public CustomAuthenticationFilter(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // try to retrieve user from token to security context
        filter(request, response);

        filterChain.doFilter(request, response);
    }

    private void filter(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        Optional<String> token = getTokenFromCookies(request);

        if (token.isPresent()) {
            authenticate(token.get(), response);
        }
    }

    private void authenticate(@NonNull String token, @NonNull HttpServletResponse response) {
        try {
            attemptAuthentication(token);
        } catch (BadCredentialsException e) {
            // Clear the token cookie if authentication fails
            Cookie cookie = new Cookie(TOKEN_COOKIE, "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    public void attemptAuthentication(String token) {

        int userId = JWT.decode(token).getClaim("user_id").asInt();

        UserDetailsResponse userDetails = authorizationService.getUserDetails(userId, token);
        if (userDetails == null || userDetails.getGroups() == null) {
            throw new BadCredentialsException("Failed to fetch user details: No roles found for the user.");
        }

        // Extract roles from the group numbers (returned by Artemis)
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Integer groupId : userDetails.getGroups()) {
            // Convert group IDs to roles (e.g., group 1 -> ROLE_ADMIN, group 2 -> ROLE_USER, etc.)
            String role = mapGroupIdToRole(groupId);
            authorities.add(new SimpleGrantedAuthority(role));
        }

        // Create an authentication token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, token, authorities);

        //Create a security context and set the authentication token
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticationToken);
        SecurityContextHolder.setContext(context);
    }

    private String mapGroupIdToRole(Integer groupId) {
        // Map group IDs from Artemis to roles in your system
        return switch (groupId) {
            case 1 -> "ROLE_ADMIN";
            case 2 -> "ROLE_USER";
            default -> "ROLE_NO_ACCESS"; // Default role if no specific mapping
        };
    }

    private Optional<String> getTokenFromCookies(@NonNull HttpServletRequest request) {
        Cookie[] cookies = request.getCookies() != null ? request.getCookies() : new Cookie[0];
        return Arrays.stream(cookies)
                .filter(cookie -> TOKEN_COOKIE.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();
    }
}
