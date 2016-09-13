package com.bankdemo.service;

import com.bankdemo.model.account.Person;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
public interface PersonService {

    void addPerson(Person person);

    void updatePerson(Person person);

    Person getPerson(int id);

    void deletePerson(int id);

    List<Person> getPersons();

    Person getPersonByIndividualPersonalNumber(String individualPersonalNumber);
}
