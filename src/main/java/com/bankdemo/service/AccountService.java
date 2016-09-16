package com.bankdemo.service;

import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.account.Account;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
public interface AccountService {

    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    void addAccount(Account account);

    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    void updateAccount(Account account);

    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    Account getAccount(int id);

    @PreAuthorize("hasRole('ROLE_ACCOUNT_INFO') or hasRole('ROLE_ACCOUNT_ALL')")
    void deleteAccount(int id);

    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    List<Account> getAccounts();

    @PreAuthorize("hasRole('ROLE_ACCOUNT_ALL')")
    Account getAccountByIban(String iban);
}
