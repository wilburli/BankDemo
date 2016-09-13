package com.bankdemo.bank.account.impl;

import com.bankdemo.bank.account.PersonProcesses;
import com.bankdemo.enums.Gender;
import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.exceptions.EmptyResultException;
import com.bankdemo.model.account.Person;
import com.bankdemo.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
@Component
public class PersonProcessesImpl implements PersonProcesses {

    @Autowired
    private PersonService personService;

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public Person addPerson(String firstname, String middlename, String lastname, String individualpersonalnumber, Date birthdate, String gender) throws ApplicationException {
        Person person = new Person();
        person.setFirstName(firstname);
        person.setMiddleName(middlename);
        person.setLastName(lastname);
        person.setIndividualPersonalNumber(individualpersonalnumber);
        person.setBirthDate(birthdate);
        Gender personGender = Gender.valueOf(gender);
        if (personGender == null) {
            logger.error("Cannot cast value to gender " + gender);
            throw new EmptyResultException("Cannot cast value to gender " + gender);
        }
        person.setGender(personGender);
        personService.addPerson(person);
        logger.info("Person created " + person);
        return person;
    }

    @Override
    public Person updatePerson(Integer id, String firstname, String middlename, String lastname, Date birthdate, String gender) throws ApplicationException {
        Person person = personService.getPerson(id);
        if (person == null) {
            logger.error("Person not found with id " + id);
            throw new EmptyResultException("Person not found with id " + id);
        }
        person.setFirstName(firstname);
        person.setMiddleName(middlename);
        person.setLastName(lastname);
        person.setBirthDate(birthdate);
        Gender personGender = Gender.valueOf(gender);
        if (personGender == null) {
            logger.error("Cannot cast value to gender " + gender);
            throw new EmptyResultException("Cannot cast value to gender " + gender);
        }
        person.setGender(personGender);
        personService.updatePerson(person);
        logger.info("Person updated " + person);
        return person;
    }

    @Override
    public Person getPerson(Integer id) throws ApplicationException {
        return personService.getPerson(id);
    }

    @Override
    public void deletePerson(Integer id) throws ApplicationException {
        personService.deletePerson(id);
    }

    @Override
    public Person getPersonByIndividualPersonalNumber(String individualpersonalnumber) throws ApplicationException {
        return personService.getPersonByIndividualPersonalNumber(individualpersonalnumber);
    }

    @Override
    public List<Person> getPersons() throws ApplicationException {
        return personService.getPersons();
    }
}
