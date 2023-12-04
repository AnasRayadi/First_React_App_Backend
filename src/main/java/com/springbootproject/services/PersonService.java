package com.springbootproject.services;

import com.springbootproject.dao.PersonDao;
import com.springbootproject.exception.DuplicateResourceException;
import com.springbootproject.exception.RequestValidationException;
import com.springbootproject.exception.ResourceNotFoundException;
import com.springbootproject.models.Person;
import com.springbootproject.requests.PersonRegistrationRequest;
import com.springbootproject.requests.PersonUpdateRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;

@Service //@Component is the same as @Service
public class PersonService {
    private PersonDao personDao;

    public PersonService(@Qualifier("jpa") PersonDao personDao) {
        this.personDao = personDao;
    }
    public List<Person> getAllPersons(){
        return personDao.selectAllPerson();
    }
    public Person getPersonById(Integer id){
        return personDao.selectPersonById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person with id [%s] not found".formatted(id)));
    }
    public void addPerson(PersonRegistrationRequest personRegistrationRequest){
        //first check if name or email is taken by another Person for example
        if (personDao.existsPersonWith(personRegistrationRequest.name())){
            throw new DuplicateResourceException("Person already exist !");
        }
        Person person = new Person(personRegistrationRequest.name(),personRegistrationRequest.age());
        personDao.insertPerson(person);
    }
    public void removePerson(Integer id){
        if (!personDao.existsPersonWithId(id)){
            throw new ResourceNotFoundException("Person with id [%s] doesn't exist".formatted(id));
        }
        personDao.deletePersonWith(id);
    }

    public void updatePerson(Integer id , PersonUpdateRequest updateRequest){

        Person person = getPersonById(id); //check if the person that you want to update exist or not

        boolean change = false;
         if(updateRequest.name()!= null && !updateRequest.name().equals(person.getName())){
             //check if the name for example or email is used before by another Person
             // in my case I check by name :
             if (personDao.existsPersonWith(updateRequest.name())){
                 throw new DuplicateResourceException("Person already exist !");
             }
             person.setName(updateRequest.name());
             change = true;
         }
        if (updateRequest.age() != 0 && updateRequest.age() != person.getAge()){
            person.setAge(updateRequest.age());
            change = true;
        }
        if (!change){
            throw new RequestValidationException("No data changes found!");
        }
        personDao.updatePersonWith(person);
    }
}
