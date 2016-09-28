package com.bankdemo.external_connectors.kkb.service;

import com.bankdemo.external_connectors.kkb.service.KkbRequest;
import com.bankdemo.external_connectors.kkb.service.KkbResponse;
import com.bankdemo.external_connectors.kkb.service.KkbService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 */
@Component
public class KkbConnector {

    @Autowired
    protected ObjectMapper objectMapper;

    protected JAXBContext jaxbContext;

    private static final String Endpoint = "https://testws.homebank.kz/ServiceGate/RunService";

    private KkbService kkbService;

    @PostConstruct
    public void postConstruct() throws Exception {

        if (objectMapper == null) {
            objectMapper = new ObjectMapper()
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        }
        jaxbContext = JAXBContext.newInstance(
                KkbResponse.class
        );
        kkbService = JAXRSClientFactory.create(
                Endpoint,
                KkbService.class
        );



    }

    public KkbResponse provide(KkbRequest request) throws Exception {
        Response response = kkbService.provide(request);
        System.out.println(response.readEntity(String.class));
        return null;
    }

}
