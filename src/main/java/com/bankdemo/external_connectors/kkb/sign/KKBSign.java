package com.bankdemo.external_connectors.kkb.sign;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 28.09.2016.
 */
@Component
public class KKBSign {

    private SignatureHelper signature;

    public KKBSign() {
    }

    @PostConstruct
    public void postConstruct() {
        String keystore="C:\\cert\\keys_rsa.jks";
        String alias="cert";
        String keypass="123456";
        String storepass="123456";

        try {
            signature = new SignatureHelper(keystore, alias, storepass, keypass);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String sign(String xmlContent) {
        String keystore="C:\\cert\\keys_rsa.jks";
        String alias="cert";
        String keypass="123456";
        String storepass="123456";
        try {
            XMLDigitalSignature xmlDigitalSignature = new XMLDigitalSignature(keystore, storepass, alias, keypass);
            return xmlDigitalSignature.SignXMLString(xmlContent);
            //return signature.signXMLString(xmlContent);
        } catch (ParserConfigurationException | SAXException | IOException |
                XPathException | KeyStoreException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException |
                InvalidAlgorithmParameterException | MarshalException | XMLSignatureException | TransformerException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public class SignatureHelper {

        private static final String KEY_STORE_TYPE = "JKS";
        private static final String PATH = "request";

        private final String PROVIDER_NAME;
        private final XMLSignatureFactory XML_SIGNATURE_FACTORY;

        private String keyStoreLocation;
        private String keyAlias;
        private String keyStorePassword;
        private String keyPassword;

        public SignatureHelper(String keyStoreLocation, String keyAlias, String keyStorePassword, String keyPassword)
                throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {

            PROVIDER_NAME = System.getProperty("jsr105Provider", "org.jcp.xml.dsig.internal.dom.XMLDSigRI");
            XML_SIGNATURE_FACTORY = XMLSignatureFactory.getInstance("DOM", (Provider)Class.forName(PROVIDER_NAME).newInstance());

            this.keyStoreLocation = keyStoreLocation;
            this.keyAlias = keyAlias;
            this.keyStorePassword = keyStorePassword;
            this.keyPassword = keyPassword;
        }

        private Document getDocument(InputStream content, boolean namespaceAware)
                throws ParserConfigurationException, SAXException, IOException {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(namespaceAware);
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            return factory.newDocumentBuilder().parse(content);
        }

        private Node getSignatureParentNode(Document doc, boolean removeChildren) throws XPathException {

            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            XPathExpression expr = xpath.compile(PATH);
            NodeList nodes = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
            if (nodes.getLength() < 1) {
                throw new XPathException("Invalid document, can't find node by PATH: " + PATH);
            }
            Node node = nodes.item(0);

            if (removeChildren) {
                NodeList nlist = node.getChildNodes();
                for (int i = 0; i < nlist.getLength(); i++) {
                    if (nlist.item(i).getNodeName().equals("ds:KkbSignature"))
                        node.removeChild(nlist.item(i));
                }
            }
            doc.normalizeDocument();

            return node;
        }

        private XMLSignature sign(Node element, PrivateKey privateKey, X509Certificate certificate)
                throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException {


            List transforms =
                    Collections.singletonList(
                            XML_SIGNATURE_FACTORY.newTransform(Transform.ENVELOPED,
                            (TransformParameterSpec)null)
                    );
            Reference ref =
                    XML_SIGNATURE_FACTORY.newReference(
                            "",
                            XML_SIGNATURE_FACTORY.newDigestMethod(DigestMethod.SHA1,
                            null),
                            transforms,
                            null,
                            null
                    );
            SignedInfo signedInfo =
                    XML_SIGNATURE_FACTORY.newSignedInfo(
                            XML_SIGNATURE_FACTORY.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec)null),
                            XML_SIGNATURE_FACTORY.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                            Collections.singletonList(ref)
                    );

            KeyInfoFactory keyInfoFactory = XML_SIGNATURE_FACTORY.getKeyInfoFactory();

            List<Serializable> x509Content = new ArrayList<Serializable>();
            x509Content.add(certificate);
            X509Data xd = keyInfoFactory.newX509Data(x509Content);

            KeyInfo keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(xd));
            DOMSignContext dsc = new DOMSignContext(privateKey, element);
            dsc.setDefaultNamespacePrefix("");

            javax.xml.crypto.dsig.XMLSignature signature = XML_SIGNATURE_FACTORY.newXMLSignature(signedInfo, null);
            signature.sign(dsc);

            return signature;
        }

        public OutputStream signXML(InputStream content)
                throws ParserConfigurationException, SAXException, IOException, XPathException, KeyStoreException,
                NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, InvalidAlgorithmParameterException,
                MarshalException, XMLSignatureException, TransformerException {

            Document doc = getDocument(content, true);
            Node sigParent = getSignatureParentNode(doc, true);
            Node nodeToSign = doc.getDocumentElement();

            // Retrieve signing key
            KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE);
            keyStore.load(new FileInputStream(keyStoreLocation), keyStorePassword.toCharArray());

            PrivateKey privateKey = (PrivateKey)keyStore.getKey(keyAlias, keyPassword.toCharArray());
            X509Certificate cert = (X509Certificate)keyStore.getCertificate(keyAlias);

            //Sign the XML
            XMLSignature signature = sign(sigParent, privateKey, cert);

            //output the resulting document
            OutputStream os = new ByteArrayOutputStream();
            Writer out = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(out));
            return os;
        }

        public String signXMLString(String xml) throws ParserConfigurationException, SAXException, IOException,
                XPathException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException,
                InvalidAlgorithmParameterException, MarshalException, XMLSignatureException, TransformerException {
            return ((ByteArrayOutputStream)signXML(new ByteArrayInputStream(xml.getBytes()))).toString("UTF-8");
        }
    }
}
