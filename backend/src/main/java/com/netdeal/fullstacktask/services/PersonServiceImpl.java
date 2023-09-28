package com.netdeal.fullstacktask.services;

import com.netdeal.fullstacktask.entities.Person;
import com.netdeal.fullstacktask.repositories.PersonRepository;
import com.netdeal.fullstacktask.utils.PasswordEncryption;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findByParentPersonIsNull();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person createPerson(Person person) {
        String passwordHash = PasswordEncryption.encode(person.getPassword());
        person.setPassword(passwordHash);
        person.setScore(0);
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person person) {
        Person existingPerson = getPersonById(id);
        boolean passCheck = PasswordEncryption.checkPassword(person.getPassword(), existingPerson.getPassword());
        return personRepository.save(existingPerson);
    }

    public void deletePerson(Long id) {
        Person person = getPersonById(id);
        personRepository.delete(person);
    }
}
