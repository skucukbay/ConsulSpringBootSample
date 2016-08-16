package com.example.consul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by skucukbay on 7/29/16.
 */
@Component
public class ConsulClientDiscovery {

    Logger log = LoggerFactory.getLogger(ConsulClientDiscovery.class);

    private final DiscoveryClient discoveryClient;

    @Autowired
    public ConsulClientDiscovery(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public void logServiceUrl() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            log.debug(service+" [");
            for (ServiceInstance instance : instances) {
                log.debug(instance.getUri().toString());
            }
            log.debug(service+" ]");


        }

    }


}
