package net.yassine.hospitalapp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        // dire à Spring Security je voudrais que toutes les requetes necessitent une authentification
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        return httpSecurity.build();
    }
}
