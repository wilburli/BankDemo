package com.bankdemo.external_connectors.kkb.info;

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
public class KkbInfoConnector {

    private static final String Endpoint = "https://testws.homebank.kz/ServiceGate/ServiceInfo";

    private KkbInfoService kkbInfoService;

    @Autowired
    private KKBSign kkbSign;

    @Autowired
    private JAXBUtils jaxbUtils;

    @PostConstruct
    public void postConstruct() throws Exception {
        kkbInfoService = JAXRSClientFactory.create(
                Endpoint,
                KkbInfoService.class
        );
    }

    public KkbInfoResponse provide(KkbInfoRequest request) throws Exception {
        KkbInfoRequest requestSigned = signRequest(request);
        requestSigned.getSignature().setXmlns("http://www.w3.org/2000/09/xmldsig#");

        Response response = kkbInfoService.provide(requestSigned);
        String responseString = response.readEntity(String.class);
        System.out.println(responseString);
        return null;
    }

    public KkbInfoRequest signRequest(KkbInfoRequest request) throws JAXBException, IOException {
        String xmlContent = jaxbUtils.marshal(request, false);
        String xmlContentSigned = kkbSign.sign(xmlContent);
        return jaxbUtils.unmarshal(xmlContentSigned, KkbInfoRequest.class);
    }
}
