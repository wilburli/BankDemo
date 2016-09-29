package com.bankdemo.external_connectors.kkb.service;

import com.bankdemo.external_connectors.kkb.commons.KKBSign;
import com.bankdemo.external_connectors.kkb.util.JAXBUtils;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
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

        Response response = kkbService.provide(requestSigned);
        String responseString = response.readEntity(String.class);
        KkbResponse kkbResponse = jaxbUtils.unmarshal(responseString, KkbResponse.class);
        return kkbResponse;
    }

    public KkbRequest signRequest(KkbRequest request) throws JAXBException, IOException {
        String xmlContent = jaxbUtils.marshal(request, false);
        String xmlContentSigned = kkbSign.sign(xmlContent);
        return jaxbUtils.unmarshal(xmlContentSigned, KkbRequest.class);
    }
}