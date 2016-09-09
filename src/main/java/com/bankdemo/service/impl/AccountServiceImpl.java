package com.bankdemo.service.impl;

import com.bankdemo.dao.AccountDAO;
import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.account.Account;
import com.bankdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public void addAccount(Account account) throws ApplicationException {
        accountDAO.addAccount(account);
    }

    @Override
    public void updateAccount(Account account) throws ApplicationException {
        accountDAO.updateAccount(account);
    }

    @Override
    public Account getAccount(int id) throws ApplicationException {
        return accountDAO.getAccount(id);
    }

    @Override
    public void deleteAccount(int id) throws ApplicationException {
        accountDAO.deleteAccount(id);
    }

    @Override
    public List<Account> getAccounts() throws ApplicationException {
        return accountDAO.getAccounts();
    }

    @Override
    public Account getAccountByIban(String iban) throws ApplicationException {
        return accountDAO.getAccountByIban(iban);
    }
}
