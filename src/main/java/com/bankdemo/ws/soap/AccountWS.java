package com.bankdemo.ws.soap;

import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.account.Account;
import com.bankdemo.util.CxfAutoRegistrationEndpointBean;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
@WebService(
        name = AccountWS.WS_NAME,
        serviceName = AccountWS.WS_SERVICE_NAME,
        portName = AccountWS.WS_PORT_NAME,
        targetNamespace = AccountWS.WS_NAMESPACE
)
@CxfAutoRegistrationEndpointBean
public interface AccountWS {

    String WS_NAME = "Account";
    String WS_SERVICE_NAME = WS_NAME + "Service";
    String WS_PORT_NAME = WS_NAME + "Port";
    String WS_NAMESPACE = "http://soap.ws.bankdemo.com/";

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    Account openAccount(
            @WebParam(name = "iban") @XmlElement(required = true) String iban,
            @WebParam(name = "currencyCode") @XmlElement(required = true) String currencyCode
    ) throws ApplicationException;;

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    void closeAccount(
            @WebParam(name = "iban") @XmlElement(required = true) String iban
    ) throws ApplicationException;;

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    void deposit(
        @WebParam(name = "iban") @XmlElement(required = true) String iban,
        @WebParam(name = "amount") @XmlElement(required = true) Double amount
    ) throws ApplicationException;

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ACCOUNT_INFO') or hasRole('ROLE_ACCOUNT_ALL')")
    Double balance(
            @WebParam(name = "iban") @XmlElement(required = true) String iban
    ) throws ApplicationException;

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    void withdraw(
            @WebParam(name = "iban") @XmlElement(required = true) String iban,
            @WebParam(name = "amount") @XmlElement(required = true) Double amount
    ) throws ApplicationException;

}
