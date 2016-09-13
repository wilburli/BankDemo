package com.bankdemo.service.impl;

import com.bankdemo.dao.PersonDAO;
import com.bankdemo.model.account.Person;
import com.bankdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Override
    public void addPerson(Person person) {
        personDAO.addPerson(person);
    }

    @Override
    public void updatePerson(Person person) {
        personDAO.updatePerson(person);
    }

    @Override
    public Person getPerson(int id) {
        return personDAO.getPerson(id);
    }

    @Override
    public void deletePerson(int id) {
        personDAO.deletePerson(id);
    }

    @Override
    public List<Person> getPersons() {
        return personDAO.getPersons();
    }

    @Override
    public Person getPersonByIndividualPersonalNumber(String individualPersonalNumber) {
        return personDAO.getPersonByIndividualPersonalNumber(individualPersonalNumber);
    }
}
