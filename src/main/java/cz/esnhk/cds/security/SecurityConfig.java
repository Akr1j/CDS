package cz.esnhk.cds.security;

import cz.esnhk.cds.service.AuthorizationService;
import cz.esnhk.cds.service.CustomAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    static final String LOGIN_ENDPOINT = "/login";
    private static final String SCRIPTS_ENDPOINT = "/scripts";
    private static final String STYLES_ENDPOINT = "/styles";

    private final CustomAuthenticationFilter customAuthenticationFilter;


    public SecurityConfig(AuthorizationService authorizationService) {
        this.customAuthenticationFilter = new CustomAuthenticationFilter(authorizationService);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable) // disable CSRF
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(this::configureAuthorizationCustomizer)
                .headers(this::configureHeadersConfigurer)
                .addFilterAfter(customAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthenticationRedirectFilter(), CustomAuthenticationFilter.class);

        return http.build();
    }

    private void configureAuthorizationCustomizer(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry customizer) {
        customizer
                .requestMatchers(LOGIN_ENDPOINT).permitAll()
                .requestMatchers(SCRIPTS_ENDPOINT + "/**").permitAll()
                .requestMatchers(STYLES_ENDPOINT + "/**").permitAll()
                .anyRequest().authenticated();
    }

    private void configureHeadersConfigurer(HeadersConfigurer<HttpSecurity> headersConfigurer) {
        headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
    }
}
