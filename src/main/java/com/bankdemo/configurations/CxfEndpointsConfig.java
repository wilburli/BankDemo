package com.bankdemo.configurations;

import com.bankdemo.ws.soap.AccountWS;
import com.bankdemo.ws.soap.UserManagerWS;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;
import java.util.Map;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
@Component
public class CxfEndpointsConfig {

    @Autowired
    private SpringBus bus;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CxfEnpointsRegistrationConfig cxfEnpointsRegistrationConfig;

    @PostConstruct
    protected void postConstruct() {

        for (Map.Entry<String, String> pair : cxfEnpointsRegistrationConfig.getCxfEndpoints().entrySet()) {
            synchronized (this) {
                EndpointImpl endpoint = new EndpointImpl(bus,null);
                endpoint.publish(pair.getKey(), applicationContext.getBean(pair.getValue()));
            }
        }

    }

}
