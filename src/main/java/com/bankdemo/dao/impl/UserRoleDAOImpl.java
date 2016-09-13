package com.bankdemo.dao.impl;

import com.bankdemo.dao.UserRoleDAO;
import com.bankdemo.model.user.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
@Repository("userRoleDAO")
public class UserRoleDAOImpl implements UserRoleDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUserRole(UserRole userRole) {
        em.persist(userRole);
    }

    @Override
    public void updateUserRole(UserRole userRole) {
        em.merge(userRole);
    }

    @Override
    public UserRole getUserRole(int id) {
        return em.find(UserRole.class, id);
    }

    @Override
    public void deleteUserRole(int id) {
       UserRole userRole = getUserRole(id);
       if (userRole != null) {
           em.remove(userRole);
       }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserRole> getUserRoles() {
        final String classname = UserRole.class.getName();
        return (List<UserRole>) em.createQuery("from " + classname + " e").getResultList();
    }
}
