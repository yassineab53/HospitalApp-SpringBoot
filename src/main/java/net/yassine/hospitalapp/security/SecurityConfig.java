package net.yassine.hospitalapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration : indiquer à Spring que c une classe de configuration
//@Configuration annotation indicates that the class has @Bean definition methods
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private  PasswordEncoder passwordEncoder;

    //creer les utilisateurs qui ont le droit d'acceder à l'application
    // ou est ce que Spring Security va chercher les utilisateurs ? il existe plusieurs strategies (InMemoryAuthentication, JDBC authentication...UserDetailsService)
    //InMemoryAuthentication : ça veut dire que je vais preciser en memoire les utilisateurs qui ont le droit d'acceder à l'application
    @Bean
    //L'annotation @Bean est utilisée en Spring Framework pour indiquer qu'une méthode retourne un objet (ou "bean") qui doit être enregistré dans le contexte de l'application Spring (Spring Application Context). Cela permet à Spring de gérer cet objet comme un composant réutilisable qui peut être injecté dans d'autres parties de l'application.
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                //quand vous preciser un password il faut utiliser un password encoder
                //les password doivent tjrs etre hachés
                //Spring Security il utilise par defaut un password encoder
                // {noop} : no password encoder : pour stocker le password sans le haché automatiquement par Spring Security


                //User.withUsername("user").password("{noop}1234").roles("USER").build(),
                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
                //User.withUsername("user2").password("{noop}1234").roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
                //User.withUsername("admin").password("{noop}1234").roles("USER", "ADMIN").build()
                User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("USER", "ADMIN").build()
                // qu'on vous travailler avec Spring Security vous aurez besoin d'utiliser un password encoder
                // alors comment utiliser le password encoder
                // on va creer un bean par exemple au nv de l'application
        );
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        //httpSecurity.formLogin();

        //personnaliser login page :
        //httpSecurity.formLogin().permitAll();
        httpSecurity.formLogin().loginPage("/login").permitAll();
        httpSecurity.authorizeRequests().requestMatchers("/user/**").hasRole("USER");
        httpSecurity.authorizeRequests().requestMatchers("/admin/**").hasRole("ADMIN");


        // dire à Spring Security je voudrais que toutes les requetes necessitent une authentification
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        //httpSecurity.exceptionHandling().accessDeniedPage("/accessDenied");
        return httpSecurity.build();
    }
}

