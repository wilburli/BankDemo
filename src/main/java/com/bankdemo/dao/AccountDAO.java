package com.bankdemo.dao;

import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.account.Account;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
public interface AccountDAO {

    void addAccount(Account account) throws ApplicationException;

    void updateAccount(Account account) throws ApplicationException;

    Account getAccount(int id) throws ApplicationException;

    void deleteAccount(int id) throws ApplicationException;

    List<Account> getAccounts() throws ApplicationException;

    Account getAccountByIban(String iban) throws ApplicationException;

}
