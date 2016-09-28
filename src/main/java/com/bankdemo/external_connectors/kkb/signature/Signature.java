package com.bankdemo.external_connectors.kkb.signature;

import com.bankdemo.external_connectors.kkb.SignedInfo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Ilyas.Kuanyshbekov on 26.09.2016.
 */
@XmlType(name = "Signature")
@XmlRootElement
public class Signature {

    @XmlAttribute(name = "xmlns")
    private String xmlns;

    @XmlElement(name = "SignedInfo")
    private SignedInfo signedInfo;

    public Signature() {
    }

    public Signature(String xmlns, SignedInfo signedInfo) {
        this.xmlns = xmlns;
        this.signedInfo = signedInfo;
    }
}
