package com.bankdemo.service;

import com.bankdemo.model.account.Person;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
public interface PersonService {

    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    void addPerson(Person person);

    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    void updatePerson(Person person);

    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL') or hasRole('ROLE_ACCOUNT_INFO')")
    Person getPerson(int id);

    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    void deletePerson(int id);

    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL') or hasRole('ROLE_ACCOUNT_INFO')")
    List<Person> getPersons();

    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL') or hasRole('ROLE_ACCOUNT_INFO')")
    Person getPersonByIndividualPersonalNumber(String individualPersonalNumber);
}
