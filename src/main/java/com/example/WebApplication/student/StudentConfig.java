package com.example.WebApplication.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student jin = new Student(
                    "Jin",
                    "email@email.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );
            Student john = new Student(
                    "John",
                    "email@email.com",
                    LocalDate.of(2005, Month.JANUARY, 10)
            );
            repository.saveAll(
                    List.of(jin, john)
            );

        };
    }
}
