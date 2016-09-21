package com.bankdemo.external_connectors.cbr.daily_rate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Ilyas.Kuanyshbekov on 21.09.2016.
 */
@Produces(MediaType.TEXT_PLAIN)
@Path("/")
public interface DailyRateRS {

    @GET
    Response provide(
        @QueryParam("date_req") String date
    );

}
