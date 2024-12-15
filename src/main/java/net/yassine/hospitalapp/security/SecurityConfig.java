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

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    //La dépendance passwordEncoder devient une variable finale, ce qui garantit qu'elle est initialisée une seule fois (lors de la construction de l'objet) et ne peut pas être modifiée par la suite.
    private  PasswordEncoder passwordEncoder;
    /*Injection par constructeur :

    Lorsque Spring instancie la classe SecurityConfig, il détecte qu'un bean de type PasswordEncoder est nécessaire (via le constructeur).
    Si un bean de type PasswordEncoder est défini dans le contexte d'application, Spring l'injecte automatiquement dans le constructeur.*/



    //creer les utilisateurs qui ont le droit d'acceder à l'application
    // ou est ce que Spring Security va chercher les utilisateurs ? il existe plusieurs strategies (InMemoryAuthentication, JDBC authentication...UserDetailsService)
    //InMemoryAuthentication : ça veut dire que je vais preciser en memoire les utilisateurs qui ont le droit d'acceder à l'application
    @Bean
    //L'annotation @Bean est utilisée en Spring Framework pour indiquer qu'une méthode retourne un objet (ou "bean") qui doit être enregistré dans le contexte de l'application Spring (Spring Application Context). Cela permet à Spring de gérer cet objet comme un composant réutilisable qui peut être injecté dans d'autres parties de l'application.
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                //quand vous preciser un password il faut utiliser un password encoder
                //les password doivent tjrs etre haches
                //Spring Security il utilise par defaut un password encoder
                // {noop} : no password encoder : pour stocker le password sans le hache automatiquement par speing security


                //User.withUsername("user").password("{noop}password").roles("USER").build(),
                User.withUsername("user").password(passwordEncoder.encode("1234")).roles("USER").build(),
                //User.withUsername("user2").password("{noop}1234").roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
                //User.withUsername("admin").password("{noop}1234").roles("USER", "ADMIN").build()
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build()
                // qu'on vous travailler avec Spring Security vous aurez besoin d'utiliser un password encoder
                // alors comment utiliser le password encoder
                // on va creer un bean par exemple au nv de l'application
        );
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.formLogin();
        //
        httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
        // dire à Spring Security je voudrais que toutes les requetes necessitent une authentification
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        return httpSecurity.build();
    }
}
