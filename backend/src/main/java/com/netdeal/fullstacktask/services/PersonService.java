package com.netdeal.fullstacktask.services;

import com.netdeal.fullstacktask.entities.Person;

import java.util.List;

public interface PersonService {

    public abstract List<Person> getAllPersons();

    public Person getPersonById(Long id);

    public Person createPerson(Person person);

    public Person updatePerson(Long id, Person person);

    public void deletePerson(Long id);

}
