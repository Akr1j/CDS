package cz.esnhk.cds.controller;

import cz.esnhk.cds.model.security.artemis_responses.AuthResponse;
import cz.esnhk.cds.service.AuthenticationService;
import cz.esnhk.cds.service.AuthorizationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static cz.esnhk.cds.service.CustomAuthenticationFilter.TOKEN_COOKIE;

@Controller
@RequestMapping("/")
public class IndexController {
    private final AuthenticationService authenticationService;
    private final AuthorizationService authorizationService;

    public IndexController(AuthenticationService authenticationService, AuthorizationService authorizationService) {
        this.authenticationService = authenticationService;
        this.authorizationService = authorizationService;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterMap().containsKey("logout")) {
            Cookie cookie = new Cookie(TOKEN_COOKIE, "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return "login";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletResponse response) {
        AuthResponse user = authenticationService.authenticate(email, password);
        //TODO Authorization just checks if the user is in the group but dont add to the security context
        if (authorizationService.authorize(user.getId(), user.getToken())) {
            response.addCookie(new Cookie(TOKEN_COOKIE, user.getToken()));
            return "redirect:/";
        }
        return "redirect:/login?error";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        // Clear the token cookie if authentication fails
        Cookie cookie = new Cookie(TOKEN_COOKIE, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/login";
    }
}
