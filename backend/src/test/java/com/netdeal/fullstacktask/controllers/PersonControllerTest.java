package com.netdeal.fullstacktask.controllers;

import com.netdeal.fullstacktask.dtos.PersonRequest;
import com.netdeal.fullstacktask.dtos.PersonResponse;
import com.netdeal.fullstacktask.entities.Person;
import com.netdeal.fullstacktask.mappers.PersonRequestMapper;
import com.netdeal.fullstacktask.mappers.PersonResponseMapper;
import com.netdeal.fullstacktask.services.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {
    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonServiceImpl personService;

    @Mock
    private PersonRequestMapper personRequestMapper;

    @Mock
    private PersonResponseMapper personResponseMapper;


    @Test
    void test_getAllPersons_returnsListOfPersonResponse() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1L, "John", "password", 100, null, null));
        persons.add(new Person(2L, "Jane", "password", 200, null, null));

        PersonResponse personResponse1 = new PersonResponse(1L, "John", 100, null);
        PersonResponse personResponse2 = new PersonResponse(2L, "Jane", 200, null);

        Mockito.when(personService.getAllPersons()).thenReturn(persons);
        Mockito.when(personResponseMapper.personToPersonResponse(persons.get(0))).thenReturn(personResponse1);
        Mockito.when(personResponseMapper.personToPersonResponse(persons.get(1))).thenReturn(personResponse2);

        ResponseEntity<List<PersonResponse>> response = personController.getAllPersons();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("John", response.getBody().get(0).getName());
        assertEquals("Jane", response.getBody().get(1).getName());
    }

    @Test
    void test_getPersonById_returnsPersonResponse() {
        Long id = 1L;
        Person person = new Person(id, "John", "password", 100, null, null);
        PersonResponse personResponse = new PersonResponse(id, "John", 100, null);


        Mockito.when(personService.getPersonById(id)).thenReturn(person);
        Mockito.when(personResponseMapper.personToPersonResponse(person)).thenReturn(personResponse);

        ResponseEntity<PersonResponse> response = personController.getPersonById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getName());
    }

    @Test
    void test_createPerson_returnsPersonResponse() {
        PersonRequest personRequest = new PersonRequest("John", "password", 1L);
        Person person = new Person(1L, "John", "password", 100, null, null);
        PersonResponse personResponse = new PersonResponse(1L, "John", 100, null);

        Mockito.when(personRequestMapper.personRequestToPerson(personRequest)).thenReturn(person);
        Mockito.when(personService.createPerson(person)).thenReturn(person);
        Mockito.when(personResponseMapper.personToPersonResponse(person)).thenReturn(personResponse);

        ResponseEntity<PersonResponse> response = personController.createPerson(personRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getName());
    }

    @Test
    void test_getAllPersons_returnsEmptyList() {
        List<Person> persons = new ArrayList<>();
        Mockito.when(personService.getAllPersons()).thenReturn(persons);

        ResponseEntity<List<PersonResponse>> response = personController.getAllPersons();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void test_getPersonById_returnsNotFound() {
        Long id = 1L;
        Mockito.when(personService.getPersonById(id)).thenReturn(null);
        Mockito.when(personResponseMapper.personToPersonResponse(null)).thenReturn(null);

        ResponseEntity<PersonResponse> response = personController.getPersonById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void test_update_person_name_only() {
        PersonRequest personRequest = new PersonRequest();
        personRequest.setName("John Doe Updated");
        personRequest.setPassword("MuitoComplicado@345@12");

        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(1L);
        personResponse.setName("John Doe Updated");
        personResponse.setScore(100);

        Person updatedPerson = new Person();
        updatedPerson.setId(1L);
        updatedPerson.setName("John Doe Updated");
        updatedPerson.setPassword("MuitoComplicado@345@12");

        Mockito.when(personRequestMapper.personRequestToPerson(personRequest)).thenReturn(updatedPerson);

        Mockito.when(personService.updatePerson(1L, updatedPerson)).thenReturn(updatedPerson);

        Mockito.when(personResponseMapper.personToPersonResponse(updatedPerson)).thenReturn(personResponse);

        ResponseEntity<PersonResponse> response = personController.updatePerson(1L, personRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personResponse, response.getBody());
    }

    @Test
    void test_successfully_delete_person_with_valid_id() {
        ResponseEntity<Void> response = personController.deletePerson(1L);

        Mockito.verify(personService).deletePerson(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}