package com.bankdemo.external_connectors.kkb.info;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 */
@Component
public class KkbInfoConnector {

    @Autowired
    protected ObjectMapper objectMapper;

    protected JAXBContext jaxbContext;

    private static final String Endpoint = "https://testws.homebank.kz/ServiceGate/ServiceInfo";

    private KkbInfoService kkbInfoService;

    @PostConstruct
    public void postConstruct() throws Exception {

        if (objectMapper == null) {
            objectMapper = new ObjectMapper()
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        }
        jaxbContext = JAXBContext.newInstance(
                KkbInfoResponse.class
        );
        kkbInfoService = JAXRSClientFactory.create(
                Endpoint,
                KkbInfoService.class
        );




    }

    public KkbInfoResponse provide(KkbInfoRequest request) throws Exception {

        JAXBContext jaxbContextRequest = JAXBContext.newInstance(
                KkbInfoRequest.class
        );
        Marshaller marshaller = jaxbContextRequest.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(request, stringWriter);

        System.out.println(stringWriter);

        Response response = kkbInfoService.provide(request);
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
        return null;
    }







}
