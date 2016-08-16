package com.example.jersey.endpoints;

import com.example.child1.BeanTestClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by skucukbay on 7/25/16.
 */
@Component
@Path("/dummy")
public class DummyEndpoint {


    private final BeanTestClass tyrBean;

    @Autowired
    public DummyEndpoint(BeanTestClass tyrBean) {
        this.tyrBean = tyrBean;
    }

    @GET
    public String testData(){
        tyrBean.testDatastore();
        return "service is running";
    }

    @GET
    @Path("/uris")
    public void testConsulClient(){
        tyrBean.testClientDiscovery();
    }

}
