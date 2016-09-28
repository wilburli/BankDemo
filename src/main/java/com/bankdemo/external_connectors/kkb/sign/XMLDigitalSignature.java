package com.bankdemo.external_connectors.kkb.sign;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import sun.security.x509.X509CertImpl;


public class XMLDigitalSignature {

    private class X509KeySelector extends KeySelector {
        public KeySelectorResult select(KeyInfo keyInfo,
                                        KeySelector.Purpose purpose,
                                        AlgorithmMethod method,
                                        XMLCryptoContext context) throws KeySelectorException {
            Iterator ki = keyInfo.getContent().iterator();
            while (ki.hasNext()) {
                XMLStructure info = (XMLStructure)ki.next();
                if (!(info instanceof X509Data))
                    continue;
                X509Data x509Data = (X509Data)info;
                Iterator xi = x509Data.getContent().iterator();
                while (xi.hasNext()) {
                    Object o = xi.next();
                    if (!(o instanceof X509CertImpl))
                        continue;
                    final PublicKey key = ((X509CertImpl)o).getPublicKey();
                    // Make sure the algorithm is compatible
                    // with the method.
                    if (algEquals(method.getAlgorithm(), key.getAlgorithm())) {
                        if (isValidCertificate((X509Certificate)o))
                            return new KeySelectorResult() {
                                public Key getKey() {
                                    return key;
                                }
                            };
                    }
                }
            }
            throw new KeySelectorException("No key found!");
        }

        private boolean algEquals(String algURI, String algName) {
            if ((algName.equalsIgnoreCase("DSA") &&
                    algURI.equalsIgnoreCase(SignatureMethod.DSA_SHA1)) ||
                    (algName.equalsIgnoreCase("RSA") &&
                            algURI.equalsIgnoreCase(SignatureMethod.RSA_SHA1))) {
                return true;
            } else {
                return false;
            }
        }

        private boolean isValidCertificate(X509Certificate certificate) {
            //TODO VALIDATE CERT AND THROW EX IF INVALID
            return true;
        }
    }

    private static final String KEY_STORE_TYPE = "JKS";
    private static final String PATH = "request";

    private String KEY_STORE_NAME = "";
    private String KEY_STORE_PASS = "";
    private String PRIVATE_KEY_PASS = "";
    private String KEY_ALIAS = "";
    private String REFERENCE_URI = "";

    private final String PROVIDER_NAME;
    private final XMLSignatureFactory XML_SIGNATURE_FACTORY;

    //////////////////////////////////////////////////////////////
    // Constructor
    //////////////////////////////////////////////////////////////

    public XMLDigitalSignature(String keyStoreLocation,
                               String keyStorePassword, String keyAlias,
                               String keyPassword) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            FileNotFoundException {
        PROVIDER_NAME =
                System.getProperty("jsr105Provider", "org.jcp.xml.dsig.internal.dom.XMLDSigRI");
        XML_SIGNATURE_FACTORY =
                XMLSignatureFactory.getInstance("DOM", (Provider)Class.forName(PROVIDER_NAME).newInstance());
        SetKeyStore(keyStoreLocation, keyStorePassword);
        SetPrivateKeyAlias(keyAlias, keyPassword);
    }


    public void SetKeyStore(String keyStoreLocation,
                            String keyStorePassword) throws FileNotFoundException {
        if (!new File(keyStoreLocation).exists())
            throw new FileNotFoundException();
        KEY_STORE_NAME = keyStoreLocation;
        KEY_STORE_PASS = keyStorePassword;
    }

    public void SetPrivateKeyAlias(String keyAlias,
                                   String keyPassword) throws FileNotFoundException {
        KEY_ALIAS = keyAlias;
        PRIVATE_KEY_PASS = keyPassword;
    }


    private Document getDocument(InputStream content,
                                 boolean namespaceAware) throws ParserConfigurationException,
            SAXException,
            IOException {
        DocumentBuilderFactory dbFactory =
                DocumentBuilderFactory.newInstance();
        dbFactory.setNamespaceAware(namespaceAware);
        dbFactory.setIgnoringComments(true);
        dbFactory.setIgnoringElementContentWhitespace(true);

        return dbFactory.newDocumentBuilder().parse(content);
    }
    //////////////////////////////////////////////////////////////
    // Sign
    //////////////////////////////////////////////////////////////

    private Node getSignatureParentNode(Document doc,
                                        boolean removeChildren) throws XPathExpressionException,
            XPathException {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        XPathExpression expr = xpath.compile(PATH);
        NodeList nodes = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
        if (nodes.getLength() < 1) {
            throw new XPathException("Invalid document, can't find node by PATH: " +
                    PATH);
        }
        Node node = nodes.item(0);

        if (removeChildren) {
            //Clear the "SignatureInformation" element to avoid duplicates
            NodeList nlist = node.getChildNodes();
            for (int i = 0; i < nlist.getLength(); i++) {
                if (nlist.item(i).getNodeName().equals("ds:Signature"))
                    node.removeChild(nlist.item(i));
            }
            //Refresh the DOM tree
        }
        doc.normalizeDocument();

        return node;
    }

    private XMLSignature Sign(Node element, PrivateKey privateKey,
                              X509Certificate certificate) throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            MarshalException,
            XMLSignatureException {
        //  TRANSFORMATIONS
        List transforms =
                Collections.singletonList(XML_SIGNATURE_FACTORY.newTransform(Transform.ENVELOPED,
                        (TransformParameterSpec)null));
        // Create a Reference to the enveloped document
        Reference ref =
                XML_SIGNATURE_FACTORY.newReference(REFERENCE_URI, XML_SIGNATURE_FACTORY.newDigestMethod(DigestMethod.SHA1,
                        null),
                        transforms, null, null);

        // Create the SignedInfo
        SignedInfo signedInfo =
                XML_SIGNATURE_FACTORY.newSignedInfo(XML_SIGNATURE_FACTORY.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
                        (C14NMethodParameterSpec)null),
                        XML_SIGNATURE_FACTORY.newSignatureMethod(SignatureMethod.RSA_SHA1,
                                null),
                        Collections.singletonList(ref));

        // Create a KeyValue containing the RSA PublicKey
        KeyInfoFactory keyInfoFactory =
                XML_SIGNATURE_FACTORY.getKeyInfoFactory();

        // Create the KeyInfo containing the X509Data.
        List<Serializable> x509Content = new ArrayList<Serializable>();
        x509Content.add(certificate);
        X509Data xd = keyInfoFactory.newX509Data(x509Content);

        // Create a KeyInfo and add the KeyValue to it
        KeyInfo keyInfo =
                keyInfoFactory.newKeyInfo(Collections.singletonList(xd));

        // Create a DOMSignContext and specify the RSA PrivateKey and
        // location of the resulting XMLSignature's parent element
        DOMSignContext dsc = new DOMSignContext(privateKey, element);
        dsc.setDefaultNamespacePrefix("");

        // Create the XMLSignature (but don't sign it yet)
        XMLSignature signature =
                XML_SIGNATURE_FACTORY.newXMLSignature(signedInfo, null);//keyInfo

        signature.sign(dsc);

        return signature;
    }

    public OutputStream SignXML(InputStream content) throws ParserConfigurationException,
            SAXException,
            IOException,
            XPathExpressionException,
            XPathException,
            KeyStoreException,
            NoSuchAlgorithmException,
            CertificateException,
            UnrecoverableKeyException,
            InvalidAlgorithmParameterException,
            MarshalException,
            XMLSignatureException,
            TransformerConfigurationException,
            TransformerException {
        Node nodeToSign = null;
        Node sigParent = null;

        // prepare signature factory
        String providerName =
                System.getProperty("jsr105Provider", "org.jcp.xml.dsig.internal.dom.XMLDSigRI");

        // Instantiate the document to be signed
        Document doc = getDocument(content, true);

        //Node where the Signature element is inserted
        sigParent = getSignatureParentNode(doc, true);

        //This is what we sign - root element
        nodeToSign = doc.getDocumentElement();

        // Retrieve signing key
        KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE);
        keyStore.load(new FileInputStream(KEY_STORE_NAME),
                KEY_STORE_PASS.toCharArray());

        PrivateKey privateKey =
                (PrivateKey)keyStore.getKey(KEY_ALIAS, PRIVATE_KEY_PASS.toCharArray());

        //Retreive the embedded certificate
        X509Certificate cert =
                (X509Certificate)keyStore.getCertificate(KEY_ALIAS);

        //Sign the XML
        XMLSignature signature = Sign(sigParent, privateKey, cert);

        //output the resulting document
        OutputStream os = new ByteArrayOutputStream();
        Writer out = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        Transformer trans = TransformerFactory.newInstance().newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(out));
        return os;
    }

    public String SignXMLFile(String file) throws ParserConfigurationException,
            SAXException, IOException,
            XPathExpressionException,
            XPathException,
            KeyStoreException,
            NoSuchAlgorithmException,
            CertificateException,
            UnrecoverableKeyException,
            InvalidAlgorithmParameterException,
            MarshalException,
            XMLSignatureException,
            TransformerConfigurationException,
            TransformerException {
        return ((ByteArrayOutputStream)SignXML(new FileInputStream(file))).toString("UTF-8");
    }

    public String SignXMLString(String xml) throws ParserConfigurationException,
            SAXException, IOException,
            XPathExpressionException,
            XPathException,
            KeyStoreException,
            NoSuchAlgorithmException,
            CertificateException,
            UnrecoverableKeyException,
            InvalidAlgorithmParameterException,
            MarshalException,
            XMLSignatureException,
            TransformerConfigurationException,
            TransformerException {
        return ((ByteArrayOutputStream)SignXML(new ByteArrayInputStream(xml.getBytes()))).toString("UTF-8");
    }
}
