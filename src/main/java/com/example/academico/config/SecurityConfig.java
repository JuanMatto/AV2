package com.example.academico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // desabilita CSRF
                .csrf(csrf -> csrf.disable())

                // headers para H2
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

                // autorizações
                .authorizeHttpRequests(auth -> auth
                        // Prometheus público
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/actuator/prometheus/").permitAll()
                        // Actuator geral com autenticação
                        .requestMatchers(EndpointRequest.toAnyEndpoint()).authenticated()
                        // H2 console e Swagger públicos
                        .requestMatchers("/", "/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // demais endpoints exigem autenticação
                        .anyRequest().authenticated()
                )

                // JWT apenas para endpoints protegidos
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(JWT->{}));

        return http.build();
    }
}
