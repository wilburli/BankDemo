package com.bankdemo;

import com.bankdemo.external_connectors.kkb.commons.KKbParameter;
import com.bankdemo.external_connectors.kkb.info.KkbInfoConnector;
import com.bankdemo.external_connectors.kkb.info.KkbInfoRequest;
import com.bankdemo.external_connectors.kkb.service.KkbConnector;
import com.bankdemo.external_connectors.kkb.service.KkbRequest;
import com.bankdemo.external_connectors.kkb.service.KkbResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;

@SpringBootApplication(scanBasePackages = {"com.bankdemo"})
public class BankDemoApplication {

    @Resource
    private Environment env;

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(new Class[] {BankDemoApplication.class}, args);

        invokeKkbRequest(context);
        //invokeKkbInfoRequest(context);
    }

    public static void invokeKkbRequest(ApplicationContext context) throws Exception {
        System.out.println("----------------------- RUN SERVICE ----------------------------");
        KkbConnector connector = (KkbConnector)context.getBean("kkbConnector");

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
        KkbResponse response1 = connector.provide(request);
        printResponse(response1);


        KkbRequest execute = new KkbRequest();
        execute.setOperType("EXEC");
        execute.setClient("KCELL");
        execute.setClientId("123456789012");
        execute.setId("2824849");
        execute.setServer("KKGB");
        execute.setServerId("600400055239");
        execute.setService("1064");
        KkbResponse response2 = connector.provide(execute);
        printResponse(response2);



    }

    public static void invokeKkbInfoRequest(ApplicationContext context) throws Exception {
        System.out.println("----------------------- INFO SERVICE ----------------------------");
        KkbInfoConnector infoConnector = (KkbInfoConnector)context.getBean("kkbInfoConnector");

        KkbInfoRequest infoRequest1 = new KkbInfoRequest();
        infoRequest1.setInfoType("GetError");
        infoRequest1.setClient("KCELL");
        infoRequest1.setClientId("123456789012");
        infoRequest1.setServer("KKGB");
        infoRequest1.setServerId("600400055239");
        infoConnector.provide(infoRequest1);
    }

    public static void printResponse(KkbResponse response) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(KkbResponse.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(response, writer);
        System.out.println(writer.toString());
    }
}
