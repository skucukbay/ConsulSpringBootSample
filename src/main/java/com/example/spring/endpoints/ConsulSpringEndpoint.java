package com.example.spring.endpoints;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by skucukbay on 7/29/16.
 */
//@Component
//@RestController
public class ConsulSpringEndpoint {

    @GET
    public String msg(){
        return "service is running";
    }

    @GET
    @RequestMapping("/health")
    public Response health(){
        return Response.ok().build();
    }
}
