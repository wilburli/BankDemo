package com.bankdemo.bank.account.impl;

import com.bankdemo.bank.account.AccountProcesses;
import com.bankdemo.enums.CurrencyCode;
import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.exceptions.EmptyResultException;
import com.bankdemo.exceptions.InterruptOperationException;
import com.bankdemo.model.account.Account;
import com.bankdemo.model.account.Person;
import com.bankdemo.service.AccountService;
import com.bankdemo.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
@Component
public class AccountProcessesImpl implements AccountProcesses {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PersonService personService;

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public Account openAccount(String iban, String currencyCode, String individualPersonalNumber) throws ApplicationException {

        CurrencyCode code = CurrencyCode.valueOf(currencyCode);
        if (code == null) {
            logger.error("CurrencyCode not found " + currencyCode);
            throw new ApplicationException("CurrencyCode not found " + currencyCode);
        }

        if (accountService.getAccountByIban(iban) != null) {
            logger.error("Account is already exists " + iban);
            throw new ApplicationException("Account already exists " + iban);
        }

        Person person = personService.getPersonByIndividualPersonalNumber(individualPersonalNumber);
        if (person == null) {
            logger.error("Person not found with individual personal number  " + individualPersonalNumber);
            throw new ApplicationException("Person not found with individual personal number  " + individualPersonalNumber);
        }

        Account account = new Account();
        account.setIban(iban);
        account.setCurrencyCode(code);
        account.setBalance(new Double(0));
        account.setBegda(new Date());
        account.setActive(true);
        account.setPerson(person);
        accountService.addAccount(account);
        logger.info("Account opened " + account);

        return account;
    }

    @Override
    public void closeAccount(String iban) throws ApplicationException {
        Account account = accountService.getAccountByIban(iban);

        if (account == null) {
            logger.error("Account not found for iban " + iban);
            throw new EmptyResultException("Account not found for iban " + iban);
        }

        Double balance = account.getBalance();
        if (balance != 0) {
            logger.error("Cannot close account, remained balance " + balance);
            throw new InterruptOperationException("Cannot close account, remainded balance " + balance);
        }

        account.setEndda(new Date());
        account.setActive(false);
        accountService.updateAccount(account);
        logger.info("Account closed " + account);
    }

    @Override
    public void deposit(String iban, Double amount) throws ApplicationException {
        Account account = accountService.getAccountByIban(iban);

        if (account == null) {
            logger.error("Account not found for iban " + iban);
            throw new EmptyResultException("Account not found for iban " + iban);
        }

        Double balance = account.getBalance();

        if (! isAccountValid(account)) {
            logger.error("Account is not valid " + account);
            throw new InterruptOperationException("Account is not valid " + account);
        }

        if (balance == null) {
            balance = 0.00;
        }

        if (amount <= 0) {
            logger.error("Specify amount for deposit " + amount);
            throw new InterruptOperationException("Specify amount for deposit " + amount);
        }

        balance += amount;
        account.setBalance(balance);

        accountService.updateAccount(account);
        logger.info("Deposit operation: " + account + " ,amount " + amount);
    }

    @Override
    public Double balance(String iban) throws ApplicationException {
        Account account = accountService.getAccountByIban(iban);
        logger.info("Balance operation: " + account);
        return account.getBalance();
    }

    @Override
    public void withdraw(String iban, Double amount) throws ApplicationException {

        if (amount <= 0) {
            logger.error("Specify amount for withdraw " + amount);
            throw new InterruptOperationException("Specify amount for withdraw " + amount);
        }

        Account account = accountService.getAccountByIban(iban);
        if (account == null) {
            logger.error("Account not found for iban " + iban);
            throw new EmptyResultException("Account not found for iban " + iban);
        }

        if (! isAccountValid(account)) {
            logger.error("Account is not valid " + account);
            throw new InterruptOperationException("Account is not valid " + account);
        }

        if (! isAmountAvailableInternal(account, amount)) {
            logger.error("Not enough money available on account " + account);
            throw new InterruptOperationException("Not enough money available on account " + account);
        }

        Double balance = account.getBalance();
        balance -= amount;
        account.setBalance(balance);

        accountService.updateAccount(account);
        logger.info("Withdraw operation: " + account + " ,amount " + amount);
    }

    private boolean isAmountAvailableInternal(Account account, Double expectedAmount) throws ApplicationException {

        if (account == null) {
            logger.error("Account is required");
            throw new ApplicationException("Account is required");
        }

        Double balance = account.getBalance();
        if (balance == null) {
            balance = 0.00;
        }

        return expectedAmount <= balance;
    }

    private boolean isAccountValid(Account account, Date date) throws ApplicationException {

        if (account == null) {
            logger.error("Account is required");
            throw new ApplicationException("Account is required");
        }

        if (! account.getActive()) {
            return false;
        }

        return true;
    }

    private boolean isAccountValid(Account account) throws ApplicationException {
        return isAccountValid(account, new Date());
    }


}
