package com.bankdemo.external_connectors.kkb.commons;

import javax.xml.bind.annotation.*;

/**
 * Created by Ilyas.Kuanyshbekov on 26.09.2016.
 */
@XmlRootElement(name = "Signature")
@XmlAccessorType(XmlAccessType.FIELD)
public class KkbSignature {

    @XmlAttribute(name = "xmlns")
    private String xmlns;

    @XmlElement(name = "SignedInfo")
    private KkbSignedInfo signedInfo;

    @XmlElement(name = "SignatureValue")
    private String signatureValue;

    public KkbSignature() {
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public void setSignedInfo(KkbSignedInfo signedInfo) {
        this.signedInfo = signedInfo;
    }

    public void setSignatureValue(String signatureValue) {
        this.signatureValue = signatureValue;
    }
}
