package com.bankdemo.ws.soap;

import com.bankdemo.exceptions.ApplicationException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
@WebService(
        name = HelloWorldWs.WS_NAME,
        serviceName = HelloWorldWs.WS_SERVICE_NAME,
        portName = HelloWorldWs.WS_PORT_NAME,
        targetNamespace = HelloWorldWs.WS_NAMESPACE
)
public interface HelloWorldWs {

    String WS_NAME = "HelloWorld";
    String WS_SERVICE_NAME = WS_NAME + "Service";
    String WS_PORT_NAME = WS_NAME + "Port";
    String WS_NAMESPACE = "http://soap.ws.bankdemo.com/";

    @WebMethod
    String greeting(@WebParam(name = "message") @XmlElement(required = true) String message) throws ApplicationException;

}
