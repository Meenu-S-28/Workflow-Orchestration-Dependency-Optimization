package com.example.myproject.Security;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    // Security Rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // Public endpoints
                        .requestMatchers("/api/auth/**").permitAll()

                        // Workflow creation
                        .requestMatchers(HttpMethod.POST, "/api/workflows/**")
                        .hasAnyRole("ADMIN", "DESIGNER")

                        // Execute workflow
                        .requestMatchers(HttpMethod.POST, "/api/workflows/*/execute")
                        .hasAnyRole("ADMIN", "OPERATOR")

                        // Complete manual task
                        .requestMatchers(HttpMethod.PATCH, "/api/task-runs/*/complete")
                        .hasAnyRole("ADMIN", "OPERATOR")

                        // View workflow runs
                        .requestMatchers(HttpMethod.GET, "/api/workflow-runs/**")
                        .hasAnyRole("ADMIN", "VIEWER", "OPERATOR", "DESIGNER")

                        .anyRequest().authenticated()
                )

                // Register JWT Filter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}