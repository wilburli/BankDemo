package com.bankdemo.external_connectors.kkb.service;

import com.bankdemo.external_connectors.kkb.signature.KkbSignature;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class KkbResponse {

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
     * Mnemonic of the operation type
     */
    @XmlAttribute(name = "operType")
    private String operType;

    /**
     * Request result
     *  0 - success
     */
    @XmlAttribute(name = "result")
    private String result;

    /**
     * Request result description
     */
    @XmlAttribute(name = "resultDescr")
    private String resultDescr;

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
     * State
     */
    @XmlAttribute(name = "state")
    private String state;

    /**
     * Description of state
     */
    @XmlAttribute(name = "state-descr")
    private String stateDescr;

    /**
     * Specific parameters
     */
    @XmlElement(name = "p")
    private List<KKbParameter> parameters = new ArrayList<>();

    /**
     * KKB Request
     */
    @XmlElementRef(name = "request")
    private KkbRequest request;

    /**
     * KKB signature
     */
    @XmlElement(name = "Signature")
    private KkbSignature signature;

    public KkbResponse() {
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultDescr() {
        return resultDescr;
    }

    public void setResultDescr(String resultDescr) {
        this.resultDescr = resultDescr;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateDescr() {
        return stateDescr;
    }

    public void setStateDescr(String stateDescr) {
        this.stateDescr = stateDescr;
    }

    public List<KKbParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<KKbParameter> parameters) {
        this.parameters = parameters;
    }

    public KkbRequest getRequest() {
        return request;
    }

    public void setRequest(KkbRequest request) {
        this.request = request;
    }

    public KkbSignature getSignature() {
        return signature;
    }

    public void setSignature(KkbSignature signature) {
        this.signature = signature;
    }
}
