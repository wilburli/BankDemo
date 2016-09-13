package com.bankdemo.ws.soap.endpoints;

import com.bankdemo.bank.account.AccountProcesses;
import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.account.Account;
import com.bankdemo.ws.soap.AccountWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
@Component
public class AccountWSEndpoint implements AccountWS {

    @Autowired
    private AccountProcesses accountProcesses;

    @Override
    public Account openAccount(@XmlElement(required = true) String iban, @XmlElement(required = true) String currencyCode, @XmlElement(required = true) String individualPersonalNumber) throws ApplicationException {
        return accountProcesses.openAccount(iban, currencyCode, individualPersonalNumber);
    }

    @Override
    public void deposit(@XmlElement(required = true) String iban, @XmlElement(required = true) Double amount) throws ApplicationException {
        accountProcesses.deposit(iban, amount);
    }

    @Override
    public Double balance(@XmlElement(required = true) String iban) throws ApplicationException {
        return accountProcesses.balance(iban);
    }

    @Override
    public void closeAccount(@XmlElement(required = true) String iban) throws ApplicationException {
        accountProcesses.closeAccount(iban);
    }

    @Override
    public void withdraw(@XmlElement(required = true) String iban, @XmlElement(required = true) Double amount) throws ApplicationException {
        accountProcesses.withdraw(iban, amount);
    }
}
