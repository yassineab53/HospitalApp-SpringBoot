package net.yassine.unsecuredhospitalappyoussfi;


import net.yassine.unsecuredhospitalappyoussfi.entities.Patient;
import net.yassine.unsecuredhospitalappyoussfi.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;


@SpringBootApplication
public class UnsecuredHospitalAppYoussfiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnsecuredHospitalAppYoussfiApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Mohamed",new Date(),false,42));
            patientRepository.save(new Patient(null,"Imane",new Date(),true,98));
            patientRepository.save(new Patient(null,"Yassine",new Date(),true,342));
            patientRepository.save(new Patient(null,"Laila",new Date(),false,123));
        };
    }

}
