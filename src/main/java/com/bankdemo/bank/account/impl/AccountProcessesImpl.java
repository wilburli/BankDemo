package com.bankdemo.bank.account.impl;

import com.bankdemo.bank.account.AccountProcesses;
import com.bankdemo.enums.CurrencyCode;
import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.account.Account;
import com.bankdemo.service.AccountService;
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

    @Override
    public Account openAccount(String iban, String currencyCode) throws ApplicationException {

        CurrencyCode code = CurrencyCode.valueOf(currencyCode);
        if (code == null) {
            throw new ApplicationException("CurrencyCode not found " + currencyCode);
        }

        Account account = new Account();
        account.setIban(iban);
        account.setCurrencyCode(code);
        account.setBalance(new Double(0));
        account.setBegda(new Date());
        account.setActive(true);
        accountService.addAccount(account);

        return account;
    }

    @Override
    public void deposit(String iban, Double amount) throws ApplicationException {
        Account account = accountService.getAccountByIban(iban);
        Double balance = account.getBalance();

        if (balance == null) {
            balance = 0.00;
        }
        balance += amount;
        account.setBalance(balance);

        accountService.updateAccount(account);
    }

    @Override
    public Double balance(String iban) throws ApplicationException {
        Account account = accountService.getAccountByIban(iban);
        return account.getBalance();
    }
}
