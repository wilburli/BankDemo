package com.bankdemo.service;

import com.bankdemo.model.user.User;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 07.09.2016.
 */
public interface UserService {

    void addUser(User user);

    void updateUser(User user);

    User getUser(int id);

    void deleteUser(int id);

    List<User> getUsers();

}