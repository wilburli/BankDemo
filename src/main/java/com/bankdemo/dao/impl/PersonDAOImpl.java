package com.bankdemo.dao.impl;

import com.bankdemo.dao.PersonDAO;
import com.bankdemo.model.account.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
@Repository("personDAO")
public class PersonDAOImpl implements PersonDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addPerson(Person person) {
        em.persist(person);
    }

    @Override
    public void updatePerson(Person person) {
        em.merge(person);
    }

    @Override
    public Person getPerson(int id) {
        return em.find(Person.class, id);
    }

    @Override
    public void deletePerson(int id) {
        Person person = getPerson(id);
        if (person != null) {
            em.remove(person);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Person> getPersons() {
        final String classname = Person.class.getName();
        return (List<Person>) em.createQuery("from " + classname + " e").getResultList();
    }

    @Override
    public Person getPersonByIndividualPersonalNumber(String individualPersonalNumber) {
        Query query = em.createNativeQuery("select id from persons where individualpersonalnumber = ?1");
        query.setParameter(1, individualPersonalNumber);
        try {
            int personId = (int) query.getSingleResult();
            return getPerson(personId);
        } catch (NoResultException e) {
            return null;
        }
    }
}
