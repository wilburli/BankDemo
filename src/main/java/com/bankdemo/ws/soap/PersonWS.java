package com.bankdemo.ws.soap;

import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.account.Person;
import com.bankdemo.util.CxfAutoRegistrationEndpointBean;
import com.bankdemo.util.DateAdapter;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
@WebService(
        name = PersonWS.WS_NAME,
        serviceName = PersonWS.WS_SERVICE_NAME,
        portName = PersonWS.WS_PORT_NAME,
        targetNamespace = PersonWS.WS_NAMESPACE
)
@CxfAutoRegistrationEndpointBean
public interface PersonWS {

    String WS_NAME = "Person";
    String WS_SERVICE_NAME = WS_NAME + "Service";
    String WS_PORT_NAME = WS_NAME + "Port";
    String WS_NAMESPACE = "http://soap.ws.bankdemo.com/";

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    Person addPerson(
            @WebParam(name = "firstname") @XmlElement(required = true) String firstname,
            @WebParam(name = "middlename") @XmlElement(required = true) String middlename,
            @WebParam(name = "lastname") @XmlElement(required = true) String lastname,
            @WebParam(name = "individualpersonalnumber") @XmlElement(required = true) String individualpersonalnumber,
            @WebParam(name = "birthdate") @XmlElement(name = "birthdate",required = true) @XmlJavaTypeAdapter(DateAdapter.class) Date birthdate,
            @WebParam(name = "gender") @XmlElement(required = true) String gender
    ) throws ApplicationException;

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    Person updatePerson(
            @WebParam(name = "person_id") @XmlElement(required = true) Integer id,
            @WebParam(name = "firstname") @XmlElement(required = true) String firstname,
            @WebParam(name = "middlename") @XmlElement(required = true) String middlename,
            @WebParam(name = "lastname") @XmlElement(required = true) String lastname,
            @WebParam(name = "birthdate") @XmlElement(name = "birthdate",required = true) @XmlJavaTypeAdapter(DateAdapter.class) Date birthdate,
            @WebParam(name = "gender") @XmlElement(required = true) String gender
    ) throws ApplicationException;

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL') or hasRole('ROLE_ACCOUNT_INFO')")
    Person getPerson(
            @WebParam(name = "person_id") @XmlElement(required = true) Integer id
    ) throws ApplicationException;

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    void deletePerson(
            @WebParam(name = "person_id") @XmlElement(required = true) Integer id
    ) throws ApplicationException;

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL') or hasRole('ROLE_ACCOUNT_INFO')")
    Person getPersonByIndividualPersonalNumber(
            @WebParam(name = "individualpersonalnumber") @XmlElement(required = true) String individualpersonalnumber
    ) throws ApplicationException;

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL') or hasRole('ROLE_ACCOUNT_INFO')")
    List<Person> getPersons() throws ApplicationException;

}
