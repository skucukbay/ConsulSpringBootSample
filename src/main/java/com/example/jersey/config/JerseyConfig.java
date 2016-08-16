package com.example.jersey.config;

import com.example.jersey.endpoints.ConsulTry;
import com.example.jersey.endpoints.DummyEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Created by skucukbay on 7/25/16.
 */
@Component
@ApplicationPath("/try")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        register(DummyEndpoint.class);
        register(ConsulTry.class);
    }
}
