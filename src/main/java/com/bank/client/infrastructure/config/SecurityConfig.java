package com.bank.client.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Deshabilitar CSRF para APIs REST
                .authorizeExchange(exchanges -> exchanges
                        // PERMITIR ACCESO A SWAGGER Y OPENAPI:
                        .pathMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/api-docs/**", // Por si cambiaste la ruta en application.yml
                                "/webjars/**"
                        ).permitAll()

                        // PERMITIR ACCESO A TUS ENDPOINTS (Para pruebas rápidas):
                        // .pathMatchers("/api/v1/clients/**").permitAll()

                        // CUALQUIER OTRA COSA REQUIERE AUTENTICACIÓN:
                        .anyExchange().permitAll()
                )
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable) // Deshabilitar popup de login básico
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable) // Deshabilitar formulario de login
                .build();
    }
}
