package com.bankdemo.ws.rest;

import com.bankdemo.model.user.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


/**
 * Created by Ilyas.Kuanyshbekov on 14.09.2016.
 */
public interface UserManagerWS {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<User> getUser(@PathVariable("id") Integer id);

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder);

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    ResponseEntity<User> updateUser(@PathVariable("id") Integer id,@RequestBody User user);

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    ResponseEntity<User> deleteUser(@PathVariable("id") Integer id,@RequestBody User user);

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    ResponseEntity<List<User>> getUsers();

}
