package com.bankdemo.external_connectors.kkb.service;

import com.bankdemo.external_connectors.kkb.sign.KKBSign;
import com.bankdemo.external_connectors.kkb.util.JAXBUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 * asd
 */
@Component
public class KkbConnector {

    private static final String Endpoint = "https://testws.homebank.kz/ServiceGate/RunService";

    private KkbService kkbService;

    @Autowired
    private KKBSign kkbSign;

    @Autowired
    private JAXBUtils jaxbUtils;

    @PostConstruct
    public void postConstruct() throws Exception {
        kkbService = JAXRSClientFactory.create(
                Endpoint,
                KkbService.class
        );
    }

    public KkbResponse provide(KkbRequest request) throws Exception {
        KkbRequest requestSigned = signRequest(request);
        requestSigned.getSignature().setXmlns("http://www.w3.org/2000/09/xmldsig#");

        status(requestSigned, "REQUEST AFTER SIGNUTURE");
        Response response = kkbService.provide(requestSigned);
        String responseString = response.readEntity(String.class);
        System.out.println("******* RESPONSE STRING " + responseString);
        KkbResponse kkbResponse = jaxbUtils.unmarshal(responseString, KkbResponse.class);
        return kkbResponse;
    }

    public KkbRequest signRequest(KkbRequest request) throws JAXBException, IOException {
        String xmlContent = jaxbUtils.marshal(request, false);
        String xmlContentSigned = kkbSign.sign(xmlContent);
        return jaxbUtils.unmarshal(xmlContentSigned, KkbRequest.class);
    }

    public static void status(Object obj, String message) throws Exception {
        JAXBUtils utils = new JAXBUtils();
        System.out.println("******* " + message);
        System.out.println(utils.marshal(obj, false));
    }
}
