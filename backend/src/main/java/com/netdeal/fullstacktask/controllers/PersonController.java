package com.netdeal.fullstacktask.controllers;

import com.netdeal.fullstacktask.dtos.PersonRequest;
import com.netdeal.fullstacktask.entities.Person;
import com.netdeal.fullstacktask.mappers.PersonRequestMapper;
import com.netdeal.fullstacktask.services.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    private final PersonRequestMapper personMapper;

    @GetMapping("/")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/")
    public Person createPerson(@RequestBody @Valid PersonRequest personRequest) {
        Person person = personMapper.personRequestToPerson(personRequest);
        return personService.createPerson(person);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
