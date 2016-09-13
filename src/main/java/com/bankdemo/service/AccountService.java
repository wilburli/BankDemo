package com.bankdemo.service;

import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.account.Account;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
public interface AccountService {

    void addAccount(Account account);

    void updateAccount(Account account);

    Account getAccount(int id);

    void deleteAccount(int id);

    List<Account> getAccounts();

    Account getAccountByIban(String iban);
}
