package com.springbootproject.dao;

import com.springbootproject.models.Person;
import com.springbootproject.repositories.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class PersonJPADataAccessService implements PersonDao{
    private final PersonRepository personRepository;

    public PersonJPADataAccessService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> selectAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> selectPersonById(Integer id) {
        return personRepository.findById(id);
    }

    @Override
    public boolean existsPersonWith(String name) {
        return personRepository.existsPersonByName(name);
    }
    @Override
    public void insertPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deletePersonWith(Integer id) {
        personRepository.deleteById(id);
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return personRepository.existsPersonById(id);
    }

    @Override
    public void updatePersonWith(Person update) {
        personRepository.save(update);
    }
}
