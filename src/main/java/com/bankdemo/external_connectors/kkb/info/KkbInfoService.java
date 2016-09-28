package com.bankdemo.external_connectors.kkb.info;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Ilyas.Kuanyshbekov on 22.09.2016.
 */
@Produces(MediaType.TEXT_PLAIN)
@Path("/")
public interface KkbInfoService {

    @POST
    Response provide(
            @XmlElement KkbInfoRequest request
    );

}
