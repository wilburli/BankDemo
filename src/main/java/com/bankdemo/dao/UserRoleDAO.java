package com.bankdemo.dao;

import com.bankdemo.model.user.UserRole;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 07.09.2016.
 */
public interface UserRoleDAO {

    void addUserRole(UserRole userRole);

    void updateUserRole(UserRole userRole);

    UserRole getUserRole(int id);

    void deleteUserRole(int id);

    List<UserRole> getUserRole();
}
