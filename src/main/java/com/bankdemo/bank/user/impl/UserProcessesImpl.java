package com.bankdemo.bank.user.impl;

import com.bankdemo.bank.user.UserProcesses;
import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.exceptions.EmptyResultException;
import com.bankdemo.model.user.User;
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
}
