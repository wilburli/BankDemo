package com.basis.ws.rest.controllers;

import com.basis.ws.rest.HelloWorldWs;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
@RestController
@RequestMapping("/hello")
public class HelloWorldWsController implements HelloWorldWs {

    @RequestMapping("/greeting")
    @Override
    public String greeting(@RequestParam(value = "message") String message) {
        return message;
    }
}
