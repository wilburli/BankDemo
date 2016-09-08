package com.basis.ws.soap.endpoints;

import com.basis.exceptions.ApplicationException;
import com.basis.ws.soap.HelloWorldWs;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
@Component
public class HelloWorldWsEndpoint implements HelloWorldWs {

    @Override
    public String greeting(@XmlElement(required = true) String message) throws ApplicationException {
        return "Hello " + message;
    }
}
