package com.bankdemo.dao.impl;

import com.bankdemo.dao.AccountDAO;
import com.bankdemo.model.account.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addAccount(Account account) {
        em.persist(account);
    }

    @Override
    public void updateAccount(Account account) {
        em.merge(account);
    }

    @Override
    public Account getAccount(int id) {
        return em.find(Account.class, id);
    }

    @Override
    public void deleteAccount(int id) {
        Account account = getAccount(id);
        if (account != null) {
            em.remove(account);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Account> getAccounts() {
        final String classname = Account.class.getName();
        return (List<Account>) em.createQuery("from " + classname + " e").getResultList();
    }

    @Override
    public Account getAccountByIban(String iban) {
        Query query = em.createNativeQuery("select id from accounts where iban = ?1");
        query.setParameter(1,iban);

        try {
            int accountId = (int) query.getSingleResult();
            return getAccount(accountId);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Account> getAccountsByPersonId(Integer id) {
        Query query = em.createNativeQuery("select id from accounts where person_id = ?1");
        query.setParameter(1, id);

        List<Account> result = new ArrayList<>();

        try {
            List<Integer> list = (List<Integer>) query.getResultList();
            for (int i : list) {
                Account account = getAccount(i);
                if (account != null) {
                    result.add(account);
                }
            }

        } catch (NoResultException e) {
        }

        return result;
    }
}
