package com.bankdemo.external_connectors.kkb.info;

import com.bankdemo.external_connectors.kkb.commons.KKbParameter;
import com.bankdemo.external_connectors.kkb.commons.KkbSignature;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 */
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class KkbInfoRequest {

    /**
     * Content type
     */
    @XmlAttribute(name = "info-type")
    private String infoType;

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
    private List<KKbParameter> parameters = new ArrayList<>();

    /**
     * KKB signature
     */
    @XmlElement(name = "Signature")
    private KkbSignature signature;


    public KkbInfoRequest() {
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
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
