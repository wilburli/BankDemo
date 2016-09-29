package com.bankdemo;

import com.bankdemo.external_connectors.kkb.service.KKbParameter;
import com.bankdemo.external_connectors.kkb.service.KkbConnector;
import com.bankdemo.external_connectors.kkb.service.KkbRequest;
import com.bankdemo.external_connectors.kkb.service.KkbResponse;
import com.bankdemo.external_connectors.kkb.util.JAXBUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@SpringBootApplication(scanBasePackages = {"com.bankdemo"})
public class BankDemoApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(new Class[] {BankDemoApplication.class}, args);

        invokeKkbRequest(context);

    }

    public static void invokeKkbRequest(ApplicationContext context) throws Exception {
        KkbRequest request = new KkbRequest();
        request.setOperType("CHECK");
        request.setClient("KCELL");
        request.setClientId("123456789012");
        request.setId("2824849");
        request.setServer("KKGB");
        request.setServerId("600400055239");
        request.setService("1064");
        request.getParameters().add(new KKbParameter("CARD_ID", "1234567"));
        request.getParameters().add(new KKbParameter("PHONE_FROM", "7059034809"));
        request.getParameters().add(new KKbParameter("PHONE_TO", "7022991577"));
        request.getParameters().add(new KKbParameter("AMOUNT", "2000"));
        request.getParameters().add(new KKbParameter("CURRENCY", "KZT"));

        KkbConnector connector = (KkbConnector)context.getBean("kkbConnector");
        KkbResponse response = connector.provide(request);

        JAXBContext jaxbContext = JAXBContext.newInstance(KkbResponse.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(response, writer);
        System.out.println(writer.toString());
    }

}
