package com.onlinevoting.online_voting_system.config;

// import org.springframework.security.config.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.onlinevoting.online_voting_system.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

            // Public APIs
            .requestMatchers(
                    "/users/register",
                    "/users/login"
            ).permitAll()

            // ADMIN APIs
            .requestMatchers("/users/**").hasRole("ADMIN")
            .requestMatchers("/candidates/**").hasRole("ADMIN")
            .requestMatchers("/elections/**").hasRole("ADMIN")

            // VOTER APIs
            .requestMatchers("/votes/**").hasRole("VOTER")

            // Both ADMIN and VOTER
            .requestMatchers("/results/**")
            .hasAnyRole("ADMIN", "VOTER")

            .anyRequest().authenticated()
        )

            .addFilterBefore(
            jwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    

    

    
}
