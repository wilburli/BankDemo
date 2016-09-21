package com.bankdemo.rest_client;

import com.google.gson.Gson;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

/**
 * Created by Ilyas.Kuanyshbekov on 20.09.2016.
 */
public class RestClient {

    public static void main(String[] args) throws Exception {

        HttpAuthenticationFeature authenticationFeature = HttpAuthenticationFeature.universalBuilder()
                .credentialsForBasic("admin","admin")
                .build();


        Client client = ClientBuilder.newClient();
        client.register(authenticationFeature);

        String target = "http://localhost:8080/rest/person";

        Response response = client.target(target).path("/4").request().get();
        String json = response.readEntity(String.class);


        Gson gson = new Gson();
        PersonDTO personDTO = gson.fromJson(json, PersonDTO.class);

        System.out.println(response);
        System.out.println(personDTO);




    }

}
