package com.example.child1;

import com.example.consul.ConsulClientDiscovery;
import com.example.repo.RepoTryClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by skucukbay on 7/25/16.
 */
@Component
public class BeanTestClass {
    @Value(value = "${app.name}")
    private String appName;

    private final RepoTryClass repoClass;

    @Autowired
    private ConsulClientDiscovery discovery;

    @Autowired
    public BeanTestClass(RepoTryClass repoClass) {
        this.repoClass = repoClass;
    }

    private static Logger logger = LoggerFactory.getLogger(BeanTestClass.class);


    public void testDatastore(){
        repoClass.test();
    }

    public void testClientDiscovery(){
        discovery.logServiceUrl();
    }

}
