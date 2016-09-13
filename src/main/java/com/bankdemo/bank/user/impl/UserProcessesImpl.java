package com.bankdemo.bank.user.impl;

import com.bankdemo.bank.user.UserProcesses;
import com.bankdemo.enums.Role;
import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.exceptions.EmptyResultException;
import com.bankdemo.model.user.User;
import com.bankdemo.model.user.UserRole;
import com.bankdemo.service.UserRoleService;
import com.bankdemo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
@Component
public class UserProcessesImpl implements UserProcesses {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public User addUser(String username, String password) throws ApplicationException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.addUser(user);
        logger.info("New user created " + user);
        return user;
    }

    @Override
    public User updateUser(Integer id, String username, String password) throws ApplicationException {
        User user = userService.getUser(id);
        if (user == null){
            logger.error("User not found for id " + id);
            throw new EmptyResultException("User not found for id " + id);
        }
        user.setUsername(username);
        user.setPassword(password);
        userService.updateUser(user);
        logger.info("User updated " + user);
        return user;
    }

    @Override
    public void deleteUser(Integer id) throws ApplicationException {
        userService.deleteUser(id);
        logger.info("User deleted with id " + id);
    }

    @Override
    public User getUser(Integer id) throws ApplicationException {
        return userService.getUser(id);
    }

    @Override
    public List<User> getUsers() throws ApplicationException {
        return userService.getUsers();
    }

    @Override
    public UserRole addUserRole(Integer userId, Role role) throws ApplicationException {
        User user = userService.getUser(userId);
        if (user == null) {
            logger.error("User not found for id " + userId);
            throw new EmptyResultException("User not found for id " + userId);
        }

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleService.addUserRole(userRole);
        logger.info("New user role created " + userRole);
        return userRole;
    }

    @Override
    public UserRole updateUserRole(Integer roleId, Integer userId, Role role) throws ApplicationException {
        UserRole userRole = userRoleService.getUserRole(roleId);
        if (userRole == null) {
            logger.error("UserRole not found for id " + roleId);
            throw new EmptyResultException("UserRole not found for id " + roleId);
        }

        User user = userService.getUser(userId);
        if (user == null) {
            logger.error("User not found for id " + userId);
            throw new EmptyResultException("User not found for id " + userId);
        }
        userRole.setUser(user);
        userRole.setRole(role);

        userRoleService.updateUserRole(userRole);
        return userRole;
    }

    @Override
    public UserRole getUserRole(int id) throws ApplicationException {
        return null;
    }

    @Override
    public void deleteUserRole(int id) throws ApplicationException {

    }

    @Override
    public List<UserRole> getUserRoles() throws ApplicationException {
        return null;
    }
}
