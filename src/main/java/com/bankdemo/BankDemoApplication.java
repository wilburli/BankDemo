package com.bankdemo;

import com.bankdemo.external_connectors.kkb.JAXBUtils;
import com.bankdemo.external_connectors.kkb.service.KkbConnector;
import com.bankdemo.external_connectors.kkb.service.KkbRequest;
import com.bankdemo.external_connectors.kkb.service.RequestParameter;
import com.bankdemo.external_connectors.kkb.sign.KKBSign;
import com.bankdemo.external_connectors.kkb.signature.*;
import com.bankdemo.external_connectors.kkb.signature.SignedInfo;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@SpringBootApplication(scanBasePackages = {"com.bankdemo"})
public class BankDemoApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(new Class[] {BankDemoApplication.class}, args);

//        KkbInfoRequest kkbInfoRequest = new KkbInfoRequest(
//                "GetLanguages",
//                "KCELL",
//                "123456789012",
//                "KKGB",
//                 "600400055239"
//        );
//
//        KkbInfoConnector kkbInfoConnector = (KkbInfoConnector) context.getBean("kkbInfoConnector");
//        kkbInfoConnector.provide(kkbInfoRequest);


//        KkbRequest request = new KkbRequest(
//                "2824849",
//                "CHECK",
//                "1064",
//                "KCELL",
//                "123456789012",
//                "KKGB",
//                "600400055239",
//                null,
//                signature
//        );
//

        invokeKkbRequest(context);

    }

    public static void invokeKkbRequest(ApplicationContext context) throws Exception {

        JAXBUtils jaxbUtils = new JAXBUtils();

        KkbRequest request = new KkbRequest();
        request.setOperType("CHECK");
        request.setClient("KCELL");
        request.setClientId("123456789012");
        request.setId("2824849");
        request.setServer("KKGB");
        request.setServerId("600400055239");
        request.setService("1064");
        request.getParameters().add(new RequestParameter("CARD_ID", "1234567"));
        request.getParameters().add(new RequestParameter("PHONE_FROM", "7059034809"));
        request.getParameters().add(new RequestParameter("PHONE_TO", "7022991577"));
        request.getParameters().add(new RequestParameter("AMOUNT", "2000"));
        request.getParameters().add(new RequestParameter("CURRENCY", "KZT"));

        JAXBContext jaxbContext = JAXBContext.newInstance(
                KkbRequest.class
        );
        Marshaller marshaller = jaxbContext.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(request, writer);

        String sign = KKBSign.sign(writer.toString());
        System.out.println(sign);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        KkbRequest request2 = (KkbRequest) unmarshaller.unmarshal(new StringReader(sign));
        request2.getSignature().setXmlns("http://www.w3.org/2000/09/xmldsig#");

        System.out.println("Sending to server the following request");
        System.out.println(jaxbUtils.marshal(request2, false));


        KkbConnector kkbConnector = (KkbConnector) context.getBean("kkbConnector");
        kkbConnector.provide(request2);

    }



//    public static void invokeKkbRequest1(ApplicationContext context) throws Exception {
//
//        JAXBUtils jaxbUtils = new JAXBUtils();
//
//        List<SignedInfo.Reference.Transform> transforms = new ArrayList<>();
//        transforms.add(new SignedInfo.Reference.Transform("http://www.w3.org/2000/09/xmldsig#enveloped-signature"));
//        SignedInfo signedInfo = null;
////        SignedInfo signedInfo = new SignedInfo(
////                new SignedInfo.CanonicalizationMethod("http://www.w3.org/TR/2001/REC-xml-c14n-20010315"),
////                new SignedInfo.SignatureMethod("http://www.w3.org/2000/09/xmldsig#rsa-sha1"),
////                new SignedInfo.Reference(
////                        "",
////                        transforms,
////                        new SignedInfo.Reference.DigestMethod("http://www.w3.org/2000/09/xmldsig#sha1"),
////                        "4HSfc5IxNpfx0dzHfKHlIeRuC60=")
////        );
//
//
//        KkbRequest request = new KkbRequest();
//        request.setOperType("CHECK");
//        request.setClient("KCELL");
//        request.setClientId("123456789012");
//        request.setId("2824849");
//        request.setServer("KKGB");
//        request.setServerId("600400055239");
//        request.setService("1064");
//        request.getParameters().add(new RequestParameter("CARD_ID", "1234567"));
//        request.getParameters().add(new RequestParameter("PHONE_FROM", "7059034809"));
//        request.getParameters().add(new RequestParameter("PHONE_TO", "7022991577"));
//        request.getParameters().add(new RequestParameter("AMOUNT", "2000"));
//        request.getParameters().add(new RequestParameter("CURRENCY", "KZT"));
//
//        String keystore="C:\\cert\\keys_rsa.jks";
//        String alias="cert";
//        String keypass="123456";
//        String storepass="123456";
//
//        JAXBContext jaxbContext = JAXBContext.newInstance(
//                KkbRequest.class
//        );
//
//        Marshaller marshaller = jaxbContext.createMarshaller();
//        StringWriter writer = new StringWriter();
//        marshaller.marshal(request, writer);
//
//        System.out.println("XML to sign:");
//        System.out.println(jaxbUtils.marshal(request, false));
//        //comp.KKBSign kkbSign = new comp.KKBSign();
//        //String merchantSign = kkbSign.sign64(writer.toString(), keystore, alias, keypass, storepass);
//
//
//        String merchantSign = KKBSign.sign(writer.toString());
//
//        Signature signature = new Signature(
//                "http://www.w3.org/2000/09/xmldsig#",
//                signedInfo,
//                null//sign
//        );
//
//        request.setSignature(signature);
//
//
//        System.out.println("Sending request with the following XML:");
//        System.out.println(jaxbUtils.marshal(request, false));
//
//        //KkbConnector kkbConnector = (KkbConnector) context.getBean("kkbConnector");
//        //kkbConnector.provide(request);
//
//
//    }

    public static void invokeKkbRequest2(ApplicationContext context) throws Exception {

        String keystore="C:\\cert\\keys_rsa.jks";
        String alias="cert";
        String keypass="123456";
        String storepass="123456";
        comp.KKBSign kkbSign = new comp.KKBSign();


        Document pageXML = DocumentHelper.createDocument();
        Element elementRequest = pageXML.addElement("request");
        elementRequest.addAttribute("OperType", "CHECK");
        elementRequest.addAttribute("client","KCELL");
        elementRequest.addAttribute("client-id","123456789012");
        elementRequest.addAttribute("id", "2824849");
        elementRequest.addAttribute("server","KKGB");
        elementRequest.addAttribute("server-id","600400055239");
        elementRequest.addAttribute("service","1064");

        String sing = kkbSign.sign64(elementRequest.asXML(), keystore, alias, keypass, storepass);

        // Signature
        Element elementSignature = elementRequest.addElement("Signature");
        elementSignature.addAttribute("xmlns", "http://www.w3.org/2000/09/xmldsig#");
        // SignedInfo
        Element elementSignedInfo = elementSignature.addElement("SignedInfo");
        Element elementCanonicalizationMethod = elementSignedInfo.addElement("CanonicalizationMethod");
        elementCanonicalizationMethod.addAttribute("Algorithm", "http://www.w3.org/TR/2001/REC-xml-c14n-20010315");

        Element elementSignatureMethod = elementSignedInfo.addElement("SignatureMethod");
        elementSignatureMethod.addAttribute("Algorithm", "http://www.w3.org/2000/09/xmldsig#rsa-sha1");

        // Reference
        Element elementReference = elementSignedInfo.addElement("Reference");
        elementReference.addAttribute("URI", "");

        Element elementTransforms = elementReference.addElement("Transforms");
        Element elementTransform = elementTransforms.addElement("Transform");
        elementTransform.addAttribute("Algorithm", "http://www.w3.org/2000/09/xmldsig#enveloped-signature");

        Element elementDigestMethod = elementReference.addElement("DigestMethod");
        elementDigestMethod.addAttribute("Algorithm", "http://www.w3.org/2000/09/xmldsig#sha1");

        Element elementDigestValue = elementReference.addElement("DigestValue");
        elementDigestValue.addText("ElUUxQHXFT9AXkyMAQFfuQOwyJg=");

        Element elementSignatureValue = elementSignature.addElement("SignatureValue");
        elementSignatureValue.addText(sing);

        System.out.println("Invoke2: sending request");
        System.out.println(pageXML.asXML());

        String endpoint = KkbConnector.Endpoint;
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        OutputStream output = new BufferedOutputStream(connection.getOutputStream());
        output.write(pageXML.asXML().getBytes());
        output.flush();


        int responseCode = connection.getResponseCode();
        System.out.println("Response Code : " + responseCode);


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();
        //print result
        System.out.println(response.toString());

    }
}
