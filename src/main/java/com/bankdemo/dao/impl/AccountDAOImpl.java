package com.bankdemo.dao.impl;

import com.bankdemo.dao.AccountDAO;
import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.exceptions.EmptyResultException;
import com.bankdemo.model.account.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addAccount(Account account) throws ApplicationException {
        em.persist(account);
    }

    @Override
    public void updateAccount(Account account) throws ApplicationException {
        em.merge(account);
    }

    @Override
    public Account getAccount(int id) throws ApplicationException {
        return em.find(Account.class, id);
    }

    @Override
    public void deleteAccount(int id) throws ApplicationException {
        Account account = getAccount(id);
        if (account != null) {
            em.remove(account);
        } else {
            throw new EmptyResultException("Account not found for id " + id);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Account> getAccounts() throws ApplicationException {
        return em.createNativeQuery("from accounts").getResultList();
    }

    @Override
    public Account getAccountByIban(String iban) throws ApplicationException {
        Query query = em.createNativeQuery("select id from accounts where iban = ?1");
        query.setParameter(1,iban);
        int accountId = (int) query.getSingleResult();

        Account account = getAccount(accountId);
        if (account == null) {
            throw new EmptyResultException("Account not found with id " + accountId + " ,iban " + iban);
        }

        return account;
    }
}
