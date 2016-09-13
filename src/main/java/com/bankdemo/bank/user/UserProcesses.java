package com.bankdemo.bank.user;

import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.user.User;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
public interface UserProcesses {

    User addUser(String username,String password) throws ApplicationException;

    User updateUser(Integer id, String username, String password) throws ApplicationException;

    void deleteUser(Integer id) throws ApplicationException;

    User getUser(Integer id) throws ApplicationException;

    List<User> getUsers() throws ApplicationException;

}
