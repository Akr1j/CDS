package cz.esnhk.cds.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static cz.esnhk.cds.Endpoints.LOGIN_ENDPOINT;

public class AuthenticationRedirectFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        filter(request, response, filterChain);
    }

    private void filter(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
            filterChain.doFilter(request, response);
            return;
        }

        redirectUnauthenticatedUser(request, response, filterChain);
    }

    private void redirectUnauthenticatedUser(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().startsWith(LOGIN_ENDPOINT)) {
            filterChain.doFilter(request, response);
            return;
        }

        redirectToLogin(response);
    }

    private void redirectToLogin(@NonNull HttpServletResponse response) {
        String redirectURI = response.encodeRedirectURL(LOGIN_ENDPOINT);

        response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
        response.setHeader(HttpHeaders.LOCATION, redirectURI);
    }
}
