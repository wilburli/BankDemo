package com.bankdemo.external_connectors.kkb.commons;

import javax.xml.bind.annotation.*;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 */
@XmlRootElement(name = "Parameter")
@XmlAccessorType(XmlAccessType.FIELD)
public class KKbParameter {

    @XmlAttribute(name = "name")
    private String name;

    @XmlValue
    private String value;

    public KKbParameter() {
    }

    public KKbParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
