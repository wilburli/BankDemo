package com.bankdemo.service;

import com.bankdemo.model.user.UserRole;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
public interface UserRoleService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void addUserRole(UserRole userRole);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateUserRole(UserRole userRole);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    UserRole getUserRole(int id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteUserRole(int id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<UserRole> getUserRoles();
}
