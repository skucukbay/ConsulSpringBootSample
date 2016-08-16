package com.example.jersey.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by skucukbay on 7/29/16.
 */
@Component
@Path("/consul")
public class ConsulTry {

    Logger log = LoggerFactory.getLogger(ConsulTry.class);

    @GET
    public String msg(){
        return "service is running";
    }

    @GET
    @Path("/health")
    public Response health(){
        log.debug("healt check is occured..");
        return Response.ok().build();
    }
}
