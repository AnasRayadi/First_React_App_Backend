package com.springbootproject.repositories;

import com.springbootproject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer > {
    boolean existsPersonByName(String name);
    boolean existsPersonById(Integer id);

}
