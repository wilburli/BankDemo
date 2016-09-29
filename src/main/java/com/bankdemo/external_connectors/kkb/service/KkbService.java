package com.bankdemo.external_connectors.kkb.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 */
@Produces(MediaType.TEXT_PLAIN)
@Path("/")
public interface KkbService {

    @POST
    Response provide(
            @XmlElement KkbRequest request
    );
}
