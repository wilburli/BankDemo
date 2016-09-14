package com.bankdemo.ws.soap.endpoints;

import com.bankdemo.bank.user.UserProcesses;
import com.bankdemo.enums.Role;
import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.user.User;
import com.bankdemo.model.user.UserRole;
import com.bankdemo.util.RoleAdapter;
import com.bankdemo.ws.soap.UserManagerWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
@Component
public class UserManagerWSEndpoint implements UserManagerWS {

    @Autowired
    private UserProcesses userProcesses;

    @Override
    public User addUser(@XmlElement(required = true) String username, @XmlElement(required = true) String password)  throws ApplicationException {
        return userProcesses.addUser(username, password);
    }

    @Override
    public User updateUser(@XmlElement(required = true) Integer id, @XmlElement(required = true) String username, @XmlElement(required = true) String password)  throws ApplicationException {
        return userProcesses.updateUser(id, username, password);
    }

    @Override
    public void deleteUser(@XmlElement(required = true) Integer id)  throws ApplicationException {
        userProcesses.deleteUser(id);
    }

    @Override
    public User getUser(@XmlElement(required = true) Integer id) throws ApplicationException {
        return userProcesses.getUser(id);
    }

    @Override
    public List<User> getUsers() throws ApplicationException {
        return userProcesses.getUsers();
    }

    @Override
    public UserRole addUserRole(@XmlElement(required = true) Integer userId, @XmlElement(name = "role", required = true) @XmlJavaTypeAdapter(RoleAdapter.class) Role role) throws ApplicationException {
        return userProcesses.addUserRole(userId, role);
    }

    @Override
    public UserRole updateUserRole(@XmlElement(required = true) Integer roleId, @XmlElement(required = true) Integer userId, @XmlElement(name = "role", required = true) @XmlJavaTypeAdapter(RoleAdapter.class) Role role) throws ApplicationException {
        return userProcesses.updateUserRole(roleId, userId, role);
    }

    @Override
    public UserRole getUserRole(@XmlElement(required = true) int id) throws ApplicationException {
        return userProcesses.getUserRole(id);
    }

    @Override
    public List<UserRole> getUserRoles() throws ApplicationException {
        return userProcesses.getUserRoles();
    }
}
