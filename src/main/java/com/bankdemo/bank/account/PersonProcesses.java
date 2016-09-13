package com.bankdemo.bank.account;

import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.account.Person;

import java.util.Date;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
public interface PersonProcesses {

    Person addPerson(String firstname, String middlename, String lastname, String individualpersonalnumber, Date birthdate, String gender) throws ApplicationException;

    Person updatePerson(Integer id, String firstname, String middlename, String lastname, Date birthdate, String gender) throws ApplicationException;

    Person getPerson(Integer id) throws ApplicationException;

    void deletePerson(Integer id) throws ApplicationException;

    Person getPersonByIndividualPersonalNumber(String individualpersonalnumber) throws ApplicationException;

    List<Person> getPersons() throws ApplicationException;
}
