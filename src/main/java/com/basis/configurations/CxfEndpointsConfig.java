package com.basis.configurations;

import com.basis.ws.soap.HelloWorldWs;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.xml.ws.Endpoint;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
@Component
public class CxfEndpointsConfig {

    @Autowired
    SpringBus bus;

    @Autowired
    HelloWorldWs helloWorldWs;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, helloWorldWs);
        endpoint.publish("/"+HelloWorldWs.WS_SERVICE_NAME);
        return endpoint;
    }
}
