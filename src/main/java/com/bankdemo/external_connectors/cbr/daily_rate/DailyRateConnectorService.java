package com.bankdemo.external_connectors.cbr.daily_rate;

import com.bankdemo.exceptions.CbrException;
import com.bankdemo.external_connectors.util.AbstractConnectorService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ilyas.Kuanyshbekov on 21.09.2016.
 */
@Component
public class DailyRateConnectorService extends AbstractConnectorService {

    private DailyRateRS dailyRateRS;

    private static final String Endpoint = "http://www.cbr.ru/scripts/XML_daily_eng.asp";

    @PostConstruct
    public void postConstruct() throws Exception {

        if (objectMapper == null) {
            objectMapper = new ObjectMapper()
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        }
        jaxbContext = JAXBContext.newInstance(
                ValCursDTO.class
        );
        dailyRateRS = JAXRSClientFactory.create(
                Endpoint,
                DailyRateRS.class
        );

    }

    public ValCursDTO provide(Date date) throws CbrException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Response response = dailyRateRS.provide(sdf.format(date));
        return parseResponse("provide", response);
    }




}
