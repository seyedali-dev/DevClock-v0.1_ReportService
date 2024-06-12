package com.seyed.ali.reportsservice.config.security;

import com.seyed.ali.reportsservice.config.security.entrypoints.ReportServiceBearerTokenAccessDeniedHandler;
import com.seyed.ali.reportsservice.util.converter.KeycloakJwtAuthorityConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final KeycloakJwtAuthorityConverter keycloakJwtAuthorityConverter;
    private final ReportServiceBearerTokenAccessDeniedHandler reportServiceBearerTokenAccessDeniedHandler;
    private final String[] authenticatedResources = {
            "/eureka/**",
            "/actuator/**",
            "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/v3/api-docs.yaml", "/swagger-resources/**", "/webjars/**", "/aggregate/**", "/favicon.ico", "/authentication-service/v3/api-docs",
            "/h2-console/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(this.authenticatedResources).permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) // This is for H2 browser console access.
                .oauth2ResourceServer(oath2 -> oath2
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .jwtAuthenticationConverter(this.keycloakJwtAuthorityConverter)
                        )
                        .accessDeniedHandler(this.reportServiceBearerTokenAccessDeniedHandler)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
        ;

        return http.build();
    }

    // to get rid of "ROLE_"
    @Bean
    public DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler() {
        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
        defaultMethodSecurityExpressionHandler.setDefaultRolePrefix("");
        return defaultMethodSecurityExpressionHandler;
    }

}
