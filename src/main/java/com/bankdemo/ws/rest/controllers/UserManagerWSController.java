package com.bankdemo.ws.rest.controllers;

import com.bankdemo.bank.user.UserProcesses;
import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.user.User;
import com.bankdemo.service.UserService;
import com.bankdemo.ws.rest.UserManagerWS;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 14.09.2016.
 */
@RestController
public class UserManagerWSController  implements UserManagerWS {

    @Autowired
    private UserProcesses userProcesses;

    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(UserManagerWSController.class);

    @Override
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {

        User user = null;

        try {
            user = userProcesses.getUser(id);
        } catch (ApplicationException e) {
            logger.error(e.getMessage());
        }

        if (user == null) {
            logger.error("User not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {

        User persistedUser = null;

        try {
            persistedUser = userProcesses.addUser(user.getUsername(), user.getPassword());
        } catch (ApplicationException e) {
            logger.error(e.getMessage());
        }

        if (persistedUser != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponentsBuilder.path("/user/{id}").buildAndExpand(persistedUser.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } else {
            logger.error("User cannot be created");
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }


    @Override
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {

        User persistedUser = null;
        try {
            persistedUser = userProcesses.updateUser(id, user.getUsername(), user.getPassword());
        } catch (ApplicationException e) {
            logger.error(e.getMessage());
        }

        if (persistedUser == null) {
            logger.error("User cannot be updated");
            return new ResponseEntity<User>(HttpStatus.NOT_MODIFIED);
        }

        return new ResponseEntity<User>(persistedUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id, @RequestBody User user) {

        try {
            userProcesses.deleteUser(id);
        } catch (ApplicationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = null;
        try {
            users = userProcesses.getUsers();
        } catch (ApplicationException e) {
            logger.error(e.getMessage());
        }

        if (users == null) {
            logger.error("Users not found");
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

}
