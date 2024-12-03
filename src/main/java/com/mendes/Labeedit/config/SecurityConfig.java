package com.mendes.Labeedit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.mendes.Labeedit.utils.ApiRoutes;

@Configuration
@EnableConfigurationProperties

public class SecurityConfig {
    @Autowired
    private SecurityAppUserFilter securityAppUserFilter;
    private static final String[] PERMIT_ALL_LIST = {
        "v1/users/register",
        "/swagger-ui/**",
        "/swagger",
        "/v1/api-docs/**",
        "/swagger-resource/**",
        "/swagger-ui/index.html",
        "/actuator/**",
        "/v1/user/sing-in",
        "v1/user/sing-up/email"
    };
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PERMIT_ALL_LIST).permitAll()
                        .requestMatchers(HttpMethod.GET, ApiRoutes.POSTS_BASE + "/**").permitAll()
                        .anyRequest().authenticated())
                        .addFilterBefore(securityAppUserFilter, BasicAuthenticationFilter.class)

                .build();
    }
     @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
