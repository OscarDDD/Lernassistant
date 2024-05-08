package org.example.praktikumbackend.configuration;


import org.example.praktikumbackend.filter.AuthRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
@Order(1)
public class BasicAuthConfig {

    @Autowired
    private AuthRequestFilter authRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Lazy
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }



    @Bean
    public SecurityFilterChain authFilter(HttpSecurity http) throws Exception {
        // Source: https://stackoverflow.com/a/75047103/12149228
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        // Set the name of the attribute the CsrfToken will be populated on (with this we opt-in to the Spring Security 5.8 defaults)
        requestHandler.setCsrfRequestAttributeName(null);

        http
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        // authentication stuff
                        .requestMatchers("/auth/csrf").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/testtoken").authenticated()
                        .requestMatchers("auth/logout").permitAll()
                        // below here api documentation endpoints -> for now we make them public accessible
                        .requestMatchers("/api-docs.yaml").permitAll()
                        .requestMatchers("/api-docs").permitAll()
                        .requestMatchers("/v3/api-docs").permitAll()
                        .requestMatchers("/v3/api-docs.yaml").permitAll()
                        // quizzes
                        .requestMatchers("/api/quizzes/**").permitAll() // TODO make any quizzes endpoints only accessible to the lecturer
                        // usually used by students by why not should lecturer have access as well
                        .requestMatchers("/api/quiz_questions").authenticated()
                        // anything else needs authentication
                        .requestMatchers("*").authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable);

        http.addFilterBefore(authRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
