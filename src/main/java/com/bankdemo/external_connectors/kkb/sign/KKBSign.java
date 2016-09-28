package com.bankdemo.external_connectors.kkb.sign;

import org.w3c.dom.Document;
import sun.security.rsa.RSAPrivateKeyImpl;

import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import java.security.PrivateKey;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 28.09.2016.
 */
public class KKBSign {


    public static String sign(String xml) throws Exception {

        String keystore="C:\\cert\\keys_rsa.jks";
        String alias="cert";
        String keypass="123456";
        String storepass="123456";

        XMLDigitalSignature xmlDigitalSignature = new XMLDigitalSignature(keystore, storepass, alias, keypass);
        return xmlDigitalSignature.SignXMLString(xml);
    }






}
