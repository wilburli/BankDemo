package com.bankdemo.external_connectors.kkb.info;

import com.bankdemo.external_connectors.kkb.service.KkbService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 */
public class KkbInfoConnector {


    @Autowired
    protected ObjectMapper objectMapper;

    protected JAXBContext jaxbContext;

    private static final String Endpoint = "https://testws.homebank.kz/ServiceGate/InfoService";

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
