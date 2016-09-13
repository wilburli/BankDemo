package com.bankdemo.service.impl;

import com.bankdemo.dao.UserRoleDAO;
import com.bankdemo.model.user.UserRole;
import com.bankdemo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Override
    public void addUserRole(UserRole userRole) {
        userRoleDAO.addUserRole(userRole);
    }

    @Override
    public void updateUserRole(UserRole userRole) {
        userRoleDAO.updateUserRole(userRole);
    }

    @Override
    public UserRole getUserRole(int id) {
        return userRoleDAO.getUserRole(id);
    }

    @Override
    public void deleteUserRole(int id) {
        userRoleDAO.deleteUserRole(id);
    }

    @Override
    public List<UserRole> getUserRoles() {
        return userRoleDAO.getUserRoles();
    }
}
