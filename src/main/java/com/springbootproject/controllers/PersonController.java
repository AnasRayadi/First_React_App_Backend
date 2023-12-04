package com.springbootproject.controllers;

import com.springbootproject.models.Person;
import com.springbootproject.requests.PersonRegistrationRequest;
import com.springbootproject.requests.PersonUpdateRequest;
import com.springbootproject.services.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/persons")
public class PersonController {
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    //@RequestMapping(path = "api/v1/persons", method = RequestMethod.GET) //is the same as @GetMapping("api/v1/persons")
    @GetMapping
    public List<Person> getPersons(){
        return personService.getAllPersons();
    }
    @GetMapping("{personId}")
    public Person getPerson(@PathVariable("personId") int personId){
        return personService.getPersonById(personId);
    }
    @PostMapping
    public void registerPerson(@RequestBody PersonRegistrationRequest request){
        personService.addPerson(request);
    }
    @DeleteMapping("{personId}")
    public void removePerson(@PathVariable Integer personId){
        personService.removePerson(personId);
    }

    @PutMapping("{personId}")
    public void updatePerson(@PathVariable Integer personId, @RequestBody PersonUpdateRequest request){
        personService.updatePerson(personId,request);
    }
}
