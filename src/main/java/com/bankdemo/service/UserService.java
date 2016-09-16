package com.bankdemo.service;

import com.bankdemo.model.user.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 07.09.2016.
 */
public interface UserService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void addUser(User user);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateUser(User user);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    User getUser(int id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteUser(int id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> getUsers();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    User getUserByUserName(String username);
}
