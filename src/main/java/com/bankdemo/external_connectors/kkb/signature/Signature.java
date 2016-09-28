package com.bankdemo.external_connectors.kkb.signature;

import javax.xml.bind.annotation.*;

/**
 * Created by Ilyas.Kuanyshbekov on 26.09.2016.
 */
@XmlRootElement(name = "Signature")
@XmlAccessorType(XmlAccessType.FIELD)
public class Signature {

    @XmlAttribute(name = "xmlns")
    private String xmlns;

    @XmlElement(name = "SignedInfo")
    private SignedInfo signedInfo;

    @XmlElement(name = "SignatureValue")
    private String signatureValue;

    public Signature() {
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public void setSignedInfo(SignedInfo signedInfo) {
        this.signedInfo = signedInfo;
    }

    public void setSignatureValue(String signatureValue) {
        this.signatureValue = signatureValue;
    }
}
