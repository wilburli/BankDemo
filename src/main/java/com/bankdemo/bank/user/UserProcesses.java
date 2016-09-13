package com.bankdemo.bank.user;

import com.bankdemo.enums.Role;
import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.user.User;
import com.bankdemo.model.user.UserRole;

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

    UserRole addUserRole(Integer userId, Role role) throws ApplicationException;

    UserRole updateUserRole(Integer roleId, Integer userId, Role role) throws ApplicationException;

    UserRole getUserRole(int id) throws ApplicationException;

    void deleteUserRole(int id) throws ApplicationException;

    List<UserRole> getUserRoles() throws ApplicationException;
}
