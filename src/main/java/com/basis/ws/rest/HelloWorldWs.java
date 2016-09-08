package com.basis.ws.rest;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
public interface HelloWorldWs {

    public String greeting(@RequestParam(value = "message") String message);

}
