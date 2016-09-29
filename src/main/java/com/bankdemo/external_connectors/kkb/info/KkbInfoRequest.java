package com.bankdemo.external_connectors.kkb.info;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 */
@XmlRootElement(name = "request")
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

    public KkbInfoRequest() {
    }

    public KkbInfoRequest(String infoType, String client, String clientId, String server, String serverId) {
        this.infoType = infoType;
        this.client = client;
        this.clientId = clientId;
        this.server = server;
        this.serverId = serverId;
    }
}
