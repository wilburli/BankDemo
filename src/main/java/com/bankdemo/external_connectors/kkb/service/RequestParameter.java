package com.bankdemo.external_connectors.kkb.service;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 */
@XmlRootElement(name = "RequestParameter")
public class RequestParameter {

    @XmlAttribute(name = "name")
    private String name;

    @XmlValue
    private String value;

    public RequestParameter() {
    }

    public RequestParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
