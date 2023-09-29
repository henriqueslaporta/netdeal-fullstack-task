package com.netdeal.fullstacktask.services;

import com.netdeal.fullstacktask.entities.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();

    Person getPersonById(Long id);

    Person createPerson(Person person);

    Person updatePerson(Long id, Person newPerson);

    void deletePerson(Long id);

}
