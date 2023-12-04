package com.springbootproject.dao;

import com.springbootproject.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class PersonListDataAccessService implements PersonDao{
    static List<Person> personList;
    static {
        personList = new ArrayList();
        Person anas = new Person(1, "Anas", 22);
        Person rayadi = new Person(2, "Rayadi", 20);
        Person ali = new Person(3, "Ali", 30);
        personList.add(anas);
        personList.add(rayadi);
        personList.add(ali);
    }
    @Override
    public List<Person> selectAllPerson() {
        return personList;
    }

    @Override
    public Optional<Person> selectPersonById(Integer id) {
        return personList.stream().filter(p->p.getId() == id).findFirst();
    }

    @Override
    public void insertPerson(Person person) {
        personList.add(person);
    }

    @Override
    public void deletePersonWith(Integer id) {
        personList.stream().filter(p->p.getId()==id)
                    .findFirst()
                    .ifPresent(p -> personList.remove(p));
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return personList.stream().anyMatch(p->p.getId() == id);
    }

    @Override
    public boolean existsPersonWith(String name) {
        return personList.stream().anyMatch(p -> p.getName().equals(name));
    }


    @Override
    public void updatePersonWith(Person update) {

    }
}
