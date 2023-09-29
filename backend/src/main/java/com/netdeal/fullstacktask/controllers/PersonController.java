package com.netdeal.fullstacktask.controllers;

import com.netdeal.fullstacktask.dtos.PersonRequest;
import com.netdeal.fullstacktask.dtos.PersonResponse;
import com.netdeal.fullstacktask.entities.Person;
import com.netdeal.fullstacktask.mappers.PersonRequestMapper;
import com.netdeal.fullstacktask.mappers.PersonResponseMapper;
import com.netdeal.fullstacktask.services.PersonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    private final PersonRequestMapper personMapper;

    private final PersonResponseMapper personResponseMapper;

    @GetMapping("/")
    public ResponseEntity<List<PersonResponse>> getAllPersons() {
        log.info("[PersonController.getAllPersons] - Initializing");
        List<Person> persons = personService.getAllPersons();
        List<PersonResponse> responseList = persons.stream().map(personResponseMapper::personToPersonResponse).toList();
        log.info("[PersonController.getAllPersons] - Ending");
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable Long id) {
        log.info("[PersonController.getPersonById] - Initializing id: {}", id);
        PersonResponse response = personResponseMapper.personToPersonResponse(personService.getPersonById(id));
        log.info("[PersonController.getPersonById] - Ending id: {}", id);
        if (isNull(response)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody @Valid PersonRequest personRequest) {
        log.info("[PersonController.createPerson] - Initializing name: {}", personRequest.getName());
        Person person = personMapper.personRequestToPerson(personRequest);
        PersonResponse response = personResponseMapper.personToPersonResponse(personService.createPerson(person));
        log.info("[PersonController.createPerson] - Ending name: {}", personRequest.getName());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable @NotNull Long id, @RequestBody @Valid PersonRequest personRequest) {
        log.info("[PersonController.updatePerson] - Initializing id: {}", id);
        Person person = personMapper.personRequestToPerson(personRequest);
        PersonResponse response = personResponseMapper.personToPersonResponse(personService.updatePerson(id, person));
        log.info("[PersonController.updatePerson] - Ending id: {}", id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable @NotNull Long id) {
        log.info("[PersonController.deletePerson] - Initializing id: {}", id);
        personService.deletePerson(id);
        log.info("[PersonController.deletePerson] - Ending id: {}", id);
        return ResponseEntity.ok().build();
    }
}
