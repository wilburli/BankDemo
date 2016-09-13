package com.bankdemo.service;

import com.bankdemo.model.user.UserRole;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
public interface UserRoleService {

    void addUserRole(UserRole userRole);

    void updateUserRole(UserRole userRole);

    UserRole getUserRole(int id);

    void deleteUserRole(int id);

    List<UserRole> getUserRoles();
}
