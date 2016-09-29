package com.bankdemo.external_connectors.kkb.service;

import com.bankdemo.external_connectors.kkb.signature.KkbSignature;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 */
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class KkbRequest {

    /**
     * Mnemonic of the operation type
     */
    @XmlAttribute(name = "OperType")
    private String operType;

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
     * Payment reference from the portal
     */
    @XmlAttribute(name = "id")
    private String id;

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
     * service id
     */
    @XmlAttribute(name = "service")
    private String service;

    /**
     * Specific parameters
     */
    @XmlElement(name = "p")
    private List<KKbParameter> parameters = new ArrayList<>();

    /**
     * KKB signature
     */
    @XmlElement(name = "Signature")
    private KkbSignature signature;


    public KkbRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public List<KKbParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<KKbParameter> parameters) {
        this.parameters = parameters;
    }

    public KkbSignature getSignature() {
        return signature;
    }

    public void setSignature(KkbSignature signature) {
        this.signature = signature;
    }
}
