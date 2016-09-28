package com.bankdemo.external_connectors.kkb.signature;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Ilyas.Kuanyshbekov on 26.09.2016.
 */
@XmlType(name = "SignedInfo")
@XmlRootElement
public class SignedInfo {

    @XmlElement(name = "CanonicalizationMethod")
    private String canonicalizationMethod;




}
