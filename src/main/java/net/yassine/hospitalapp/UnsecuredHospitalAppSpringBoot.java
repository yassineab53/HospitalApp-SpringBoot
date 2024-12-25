package net.yassine.hospitalapp;


import net.yassine.hospitalapp.entities.Patient;
import net.yassine.hospitalapp.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;


@SpringBootApplication
public class UnsecuredHospitalAppSpringBoot {

    public static void main(String[] args) {
        SpringApplication.run(UnsecuredHospitalAppSpringBoot.class, args);
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            /*patientRepository.save(new Patient(null,"Mohamed",new Date(),false,42));
            patientRepository.save(new Patient(null,"Imane",new Date(),true,98));
            patientRepository.save(new Patient(null,"Yassine",new Date(),true,342));
            patientRepository.save(new Patient(null,"Laila",new Date(),false,123));*/
            /*Patient p1 = Patient.builder()
                    .nom("Yassine")
                    .malade(false)
                    .score(50)
                    .build();*/
            Patient p1 = new Patient(null,"Hajar", new Date(), true, 50);
            Patient p2 = new Patient(null,"Achraf", new Date(), false, 40);
            Patient p3 = new Patient(null,"Aya", new Date(), true, 20);
            Patient p4 = new Patient(null,"Salma", new Date(), false, 30);
            Patient p5 = new Patient(null,"Ahmed", new Date(), false, 60);
            Patient p6 = new Patient(null, "Mehdi", new Date(), true, 45);
            Patient p7 = new Patient(null, "Zineb", new Date(), false, 35);
            Patient p8 = new Patient(null, "Khadija", new Date(), true, 25);
            Patient p9 = new Patient(null, "Yassine", new Date(), false, 55);





            /*patientRepository.save(p1);
            patientRepository.save(p2);
            patientRepository.save(p3);
            patientRepository.save(p4);
            patientRepository.save(p5);
            patientRepository.save(p6);
            patientRepository.save(p7);
            patientRepository.save(p8);
            patientRepository.save(p9);*/


            List<Patient> patients = patientRepository.findAll();
            patients.forEach(p->{
                System.out.println(p.toString());
            });
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // parmis les password encoder qui ont utiliser on a BCryptPasswordEncoder : algorithme tres puissant qui permet de faire le hachage des mot de passes
        return new BCryptPasswordEncoder();
    }
}
