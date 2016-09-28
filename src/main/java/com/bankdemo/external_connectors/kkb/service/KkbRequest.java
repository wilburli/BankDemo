package com.bankdemo.external_connectors.kkb.service;

import com.bankdemo.external_connectors.kkb.RequestParameter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 */
@XmlRootElement(name = "request")
public class KkbRequest {

    /**
     * Payment reference from the portal
     */
    @XmlAttribute(name = "id")
    private String id;

    /**
     * Mnemonic of the operation type
     */
    @XmlAttribute(name = "OperType")
    private String operType;

    /**
     * service id
     */
    @XmlAttribute(name = "service")
    private String service;

    /**
     * Mnemonic of the client portal
     */
    @XmlAttribute(name = "client")
    private String client;

    /**
     * Client id
     */
    @XmlAttribute(name = "client-id")
    private String clientId;

    /**
     * Mnemonic of the server portal
     */
    @XmlAttribute(name = "server")
    private String server;

    /**
     * Server id
     */
    @XmlAttribute(name = "server-id")
    private String serverId;

    /**
     * Specific parameters
     */
    @XmlElement(name = "p")
    List<RequestParameter> parameters;

    public KkbRequest() {
    }

    public KkbRequest(String id, String operType, String service, String client, String clientId, String server, String serverId, List<RequestParameter> parameters) {
        this.id = id;
        this.operType = operType;
        this.service = service;
        this.client = client;
        this.clientId = clientId;
        this.server = server;
        this.serverId = serverId;
        this.parameters = parameters;
    }
}
