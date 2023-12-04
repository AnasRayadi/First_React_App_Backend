package com.springbootproject;

import com.springbootproject.repositories.PersonRepository;
import com.springbootproject.models.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    @Bean
    CommandLineRunner runner (PersonRepository personRepository) {
        return args -> {
            Person anas = new Person("Anas", 22);
            Person rayadi = new Person("Rayadi", 20);
            Person ali = new Person("Ali", 30);
            List<Person> persons = List.of(anas, rayadi, ali);
            personRepository.saveAll(persons);
        };
    }
}
