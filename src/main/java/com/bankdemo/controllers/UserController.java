package com.bankdemo.controllers;

import com.bankdemo.model.user.User;
import com.bankdemo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 19.09.2016.
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getUsers(Model model) {
        LOGGER.info("Received request to show all users");

        List<User> users = userService.getUsers();
        model.addAttribute("users", users);

        return "/users";
    }





}
