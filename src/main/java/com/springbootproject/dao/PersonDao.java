package com.springbootproject.dao;

import com.springbootproject.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDao {
    List<Person> selectAllPerson();
    Optional<Person> selectPersonById(Integer id);
    boolean existsPersonWith(String name);
    void insertPerson(Person person);
    void deletePersonWith(Integer id);
    boolean existsPersonWithId(Integer id);
    void updatePersonWith(Person update);

}
