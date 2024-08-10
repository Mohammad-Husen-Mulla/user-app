package com.app.user_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(c -> c.disable()) // Customize the csrf details if needed
                .cors(c -> c.disable()) // Customize the cors details if needed

                .authorizeHttpRequests(r -> r.requestMatchers("/api/v1/user/register").permitAll())

                .authorizeHttpRequests(r -> r.requestMatchers(HttpMethod.PUT, "/api/v1/user/update/password")
                        .hasRole("USER")) // Only user can change password

                .authorizeHttpRequests(r -> r.requestMatchers("/api/v1/user/delete/**")
                        .hasAnyRole("ADMIN", "USER"))  // Admin and user can delete the profile

                .authorizeHttpRequests(r -> r.requestMatchers(HttpMethod.PUT, "/api/v1/user/update/**")
                        .hasRole("USER")) //Only user can update his profile

                .authorizeHttpRequests(r -> r.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}