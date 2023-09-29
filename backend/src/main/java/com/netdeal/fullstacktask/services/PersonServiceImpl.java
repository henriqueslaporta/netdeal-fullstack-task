package com.netdeal.fullstacktask.services;

import com.netdeal.fullstacktask.entities.Person;
import com.netdeal.fullstacktask.repositories.PersonRepository;
import com.netdeal.fullstacktask.utils.PasswordEncryption;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;

    public List<Person> getAllPersons() {
        log.info("[PersonService.getAllPersons] - Initializing");
        List<Person> personList = personRepository.findByParentPersonIsNull();
        log.info("[PersonService.getAllPersons] - Ending");
        return personList;
    }

    public Person getPersonById(Long id) {
        log.info("[PersonService.getPersonById] - Initializing id: {}", id);
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
        log.info("[PersonService.getPersonById] - Ending id: {}", id);
        return person;
    }

    public Person createPerson(Person person) {
        log.info("[PersonService.createPerson] - Initializing name: {}", person.getName());
        person.setPassword(PasswordEncryption.encode(person.getPassword()));
        person.setScore(0);
        Person saved = personRepository.save(person);
        log.info("[PersonService.createPerson] - Ending name: {}", person.getName());
        return saved;
    }

    public Person updatePerson(Long id, Person newPerson) {
        log.info("[PersonService.updatePerson] - Initializing id: {}", id);
        Person databasePerson = getPersonById(id);
        databasePerson.setPassword(PasswordEncryption.encode(newPerson.getPassword()));
        databasePerson.setParentPerson(newPerson.getParentPerson());
        Person result = personRepository.save(newPerson);
        log.info("[PersonService.updatePerson] - Ending id: {}", id);
        return result;
    }

    public void deletePerson(Long id) {
        log.info("[PersonService.deletePerson] - Initializing id: {}", id);
        Person databasePerson = getPersonById(id);
        personRepository.delete(databasePerson);
        log.info("[PersonService.deletePerson] - Ending id: {}", id);
    }

}
