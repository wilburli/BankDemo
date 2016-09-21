package com.bankdemo.external_connectors.util;

import com.bankdemo.exceptions.CbrException;
import com.bankdemo.external_connectors.cbr.daily_rate.ValCursDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * Created by Ilyas.Kuanyshbekov on 21.09.2016.
 */
public abstract class AbstractConnectorService {

    @Autowired
    protected ObjectMapper objectMapper;

    protected JAXBContext jaxbContext;

    protected ValCursDTO parseResponse(String action, Response jaxrsResponse) throws CbrException {
        if (jaxrsResponse.getStatus() != 200) {
            throw new CbrException("Invalid status code " + jaxrsResponse.getStatus());
        }
        return parseResponseString(jaxrsResponse.readEntity(String.class));
    }

    private ValCursDTO parseResponseString(String responseString) throws CbrException {
        if (responseString.startsWith("{")) {
            throw new CbrException("Json format not supported");
        } else {
            return parseXmlString(responseString);
        }
    }

    private ValCursDTO parseXmlString(String xml) throws CbrException {
        try (StringReader reader = new StringReader(xml)) {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (ValCursDTO) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new CbrException("XML unmarshaling error", e);
        }
    }

}
