package com.bankdemo.external_connectors.kkb.service;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 */
public class RequestParameter {

    @XmlAttribute(name = "name")
    private String name;

    public RequestParameter() {
    }
}
