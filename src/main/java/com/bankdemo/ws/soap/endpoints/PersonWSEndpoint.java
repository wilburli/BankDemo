package com.bankdemo.ws.soap.endpoints;

import com.bankdemo.bank.account.PersonProcesses;
import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.account.Person;
import com.bankdemo.util.DateAdapter;
import com.bankdemo.ws.soap.PersonWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
@Component
public class PersonWSEndpoint implements PersonWS {

    @Autowired
    private PersonProcesses personProcesses;

    @Override
    public Person addPerson(@XmlElement(required = true) String firstname, @XmlElement(required = true) String middlename, @XmlElement(required = true) String lastname, @XmlElement(required = true) String individualpersonalnumber, @XmlElement(name = "birthdate",required = true) @XmlJavaTypeAdapter(DateAdapter.class) Date birthdate, @XmlElement(required = true) String gender) throws ApplicationException {
        return personProcesses.addPerson(firstname, middlename, lastname, individualpersonalnumber, birthdate, gender);
    }

    @Override
    public Person updatePerson(@XmlElement(required = true) Integer id, @XmlElement(required = true) String firstname, @XmlElement(required = true) String middlename, @XmlElement(required = true) String lastname, @XmlElement(name = "birthdate",required = true) @XmlJavaTypeAdapter(DateAdapter.class) Date birthdate, @XmlElement(required = true) String gender) throws ApplicationException {
        return personProcesses.updatePerson(id, firstname, middlename, lastname, birthdate, gender);
    }

    @Override
    public Person getPerson(@XmlElement(required = true) Integer id) throws ApplicationException {
        return personProcesses.getPerson(id);
    }

    @Override
    public void deletePerson(@XmlElement(required = true) Integer id) throws ApplicationException {
        personProcesses.deletePerson(id);
    }

    @Override
    public Person getPersonByIndividualPersonalNumber(@XmlElement(required = true) String individualpersonalnumber) throws ApplicationException {
        return personProcesses.getPersonByIndividualPersonalNumber(individualpersonalnumber);
    }

    @Override
    public List<Person> getPersons() throws ApplicationException {
        return personProcesses.getPersons();
    }
}
