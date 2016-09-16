package com.bankdemo.ws.rest;

import com.bankdemo.model.account.Account;
import com.bankdemo.model.account.Person;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 16.09.2016.
 */
public interface PersonWS {

    @RequestMapping(value = "/person/", method = RequestMethod.POST)
    ResponseEntity<Person> addPerson(@RequestBody Person person);

    @RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
    ResponseEntity<Person> updatePerson(@PathVariable("id") Integer id, @RequestBody Person person);

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deletePerson(@PathVariable("id") Integer id);

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Person> getPerson(@PathVariable("id") Integer id);

    @RequestMapping(value = "/person/", method = RequestMethod.GET)
    ResponseEntity<List<Person>> getPersons();

    @RequestMapping(value = "/person/num/", method = RequestMethod.GET)
    ResponseEntity<Person> getPersonByIndividualPersonalNumber(@RequestParam(value = "num") String individualPersonalNumber);

    @RequestMapping(value = "/person/account/{personId}", method = RequestMethod.GET)
    ResponseEntity<List<Account>> getPersonAccounts(@PathVariable("personId") Integer id);

}
