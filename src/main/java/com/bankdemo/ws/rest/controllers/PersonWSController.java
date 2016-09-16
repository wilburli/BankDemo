package com.bankdemo.ws.rest.controllers;

import com.bankdemo.model.account.Account;
import com.bankdemo.model.account.Person;
import com.bankdemo.service.AccountService;
import com.bankdemo.service.PersonService;
import com.bankdemo.ws.rest.PersonWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 16.09.2016.
 */
@RestController
public class PersonWSController implements PersonWS {

    @Autowired
    PersonService personService;

    @Autowired
    AccountService accountService;

    @Override
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Integer id, @RequestBody Person person) {

        Person persistedPerson = personService.getPerson(id);
        if (persistedPerson == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        personService.updatePerson(person);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Integer id) {
        personService.deletePerson(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Person> getPerson(@PathVariable("id") Integer id) {
        Person person = personService.getPerson(id);
        if (person == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(person, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = personService.getPersons();
        if (persons == null || persons.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(persons, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Person> getPersonByIndividualPersonalNumber(@RequestParam(value = "num") String individualPersonalNumber) {
        Person person = personService.getPersonByIndividualPersonalNumber(individualPersonalNumber);
        if (person == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(person, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Account>> getPersonAccounts(@PathVariable("personId") Integer id) {

        List<Account> accounts = accountService.getAccountsByPersonId(id);

        if (accounts == null || accounts.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(accounts, HttpStatus.OK);
    }
}
