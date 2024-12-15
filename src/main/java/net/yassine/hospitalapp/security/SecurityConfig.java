package net.yassine.hospitalapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //creer les utilisateurs qui ont le droit d'acceder à l'application
    // on va creer un objet de type InMemoryUserDetailsManager
    /*@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager{
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password("1234").roles("USER").build(),
                User.withUsername("user2").password("1234").roles("USER").build(),
                User.withUsername("admin").password("1234").roles("USER", "ADMIN").build()
        );
    }*/
    // ou est ce que Spring Security va chercher les utilisateurs ? il existe plusieurs strategies (InMemoryAuthentication, JDBC authentication...UserDetailsService)
    //InMemoryAuthentication : ça veut dire que je vais preciser en memoire les utilisateurs qui ont le droit d'acceder à l'application
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                //quand vous preciser un password il faut utiliser un password encoder
                //les password doivent tjrs etre haches
                //Spring Security il utilise par defaut un password encoder
                // {noop} : no password encoder : pour stocker le password sans le hache automatiquement par speing security
                User.withUsername("user").password("{noop}password").roles("USER").build(),
                User.withUsername("user2").password("1234").roles("USER").build(),
                User.withUsername("admin").password("1234").roles("USER", "ADMIN").build()
        );
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.formLogin();
        // dire à Spring Security je voudrais que toutes les requetes necessitent une authentification
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        return httpSecurity.build();
    }
}
