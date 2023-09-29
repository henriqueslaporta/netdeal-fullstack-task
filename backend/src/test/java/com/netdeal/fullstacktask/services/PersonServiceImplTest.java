package com.netdeal.fullstacktask.services;

import com.netdeal.fullstacktask.entities.Person;
import com.netdeal.fullstacktask.repositories.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    void test_getAllPersons_returns_list_of_persons_with_parentPerson_null() {
        List<Person> expectedPersons = new ArrayList<>();
        Person person1 = new Person();
        person1.setId(1L);
        person1.setName("John");
        person1.setPassword("password");
        person1.setScore(100);
        person1.setParentPerson(null);
        expectedPersons.add(person1);

        Person person2 = new Person();
        person2.setId(2L);
        person2.setName("Jane");
        person2.setPassword("password");
        person2.setScore(90);
        person2.setParentPerson(null);
        expectedPersons.add(person2);

        Mockito.when(personRepository.findByParentPersonIsNull()).thenReturn(expectedPersons);

        List<Person> actualPersons = personService.getAllPersons();

        assertEquals(expectedPersons, actualPersons);
    }

    @Test
    void test_getPersonById_returns_person_with_given_id() {
        Long id = 1L;
        Person expectedPerson = new Person();
        expectedPerson.setId(id);
        expectedPerson.setName("John");
        expectedPerson.setPassword("password");
        expectedPerson.setScore(100);
        expectedPerson.setParentPerson(null);

        Mockito.when(personRepository.findById(id)).thenReturn(Optional.of(expectedPerson));

        Person actualPerson = personService.getPersonById(id);

        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    void test_createPerson_saves_new_person_to_database() {
        Person person = new Person();
        person.setName("John");
        person.setPassword("password");
        person.setScore(100);
        person.setParentPerson(null);

        Person savedPerson = new Person();
        savedPerson.setId(1L);
        savedPerson.setName("John");
        savedPerson.setPassword("encoded_password");
        savedPerson.setScore(100);
        savedPerson.setParentPerson(null);

        Mockito.when(personRepository.save(person)).thenReturn(savedPerson);

        Person actualPerson = personService.createPerson(person);

        assertEquals(savedPerson, actualPerson);
    }

    @Test
    void test_getPersonById_throws_EntityNotFoundException_when_person_with_given_id_does_not_exist() {
        Long id = 1L;

        Mockito.when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> personService.getPersonById(id));
    }

    @Test
    void test_updatePerson_updatesExistingPerson() {
        Person person = new Person();
        person.setId(1L);
        person.setName("John");
        person.setPassword("password");
        person.setScore(0);

        Person updatedPerson = new Person();
        updatedPerson.setId(1L);
        updatedPerson.setName("John Doe");
        updatedPerson.setPassword("newpassword");
        updatedPerson.setScore(0);

        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        Mockito.when(personRepository.save(person)).thenReturn(updatedPerson);

        Person result = personService.updatePerson(1L, updatedPerson);

        Mockito.verify(personRepository).save(updatedPerson);

        assertEquals(updatedPerson, result);
    }

    @Test
    void test_deletePerson_deletesExistingPerson() {
        Long id = 1L;
        Person person = new Person();
        person.setId(id);
        Mockito.when(personRepository.findById(id)).thenReturn(Optional.of(person));

        personService.deletePerson(id);

        Mockito.verify(personRepository).delete(person);
    }

}