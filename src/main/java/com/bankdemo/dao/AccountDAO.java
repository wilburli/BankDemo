package com.bankdemo.dao;

import com.bankdemo.model.account.Account;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
public interface AccountDAO {

    void addAccount(Account account);

    void updateAccount(Account account);

    Account getAccount(int id);

    void deleteAccount(int id);

    List<Account> getAccounts();

    Account getAccountByIban(String iban);

    List<Account> getAccountsByPersonId(Integer id);
}
