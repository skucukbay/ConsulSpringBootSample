# Sample Project For Server Side Discovery Pattern via Consul

###Goals

*   This project is implemented as a basic proof of concept for service registry, service discovery and circuit breaker.
 -  This project can be assume as a basic architecture for mentioned approaches.
      -   At the startup, our service can connect **consul agent** and expose a healtCheck end-point for **consul agent**, 
      -   At the startup, our service can register itself to loadbalancer by using **consul-template**,
      -   At the any failure in service side, it can remove itself from available service list of load-balancer by using **consul-template**


###Required Tech  
*  [ **Consul**]((https://www.consul.io/downloads.html)) 
*   [**Consul Template**]((https://github.com/hashicorp/consul-template)) 
*   [**Spring Cloud**]((http://projects.spring.io/spring-cloud/)) 
*   [**HAProxy**](http://www.haproxy.org/) 

###Reading List For Clear Understanding
*   [Monolithic Architecture](http://microservices.io/patterns/monolithic.html)
*   [Microservices Architecture](http://microservices.io/patterns/microservices.html)
*   [Client-side service discovery](http://microservices.io/patterns/client-side-discovery.html)
*   [Server-side service discovery](http://microservices.io/patterns/server-side-discovery.html)
*   [Service registry](http://microservices.io/patterns/service-registry.html)
*   [Self Registration](http://microservices.io/patterns/self-registration.html)
     
        
###Spring Boot Notes
   In this topic, i want to talk about what I have learned to springboot .
   
 -  Springboot provides [setting profile feature](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-profiles.html) to set desired profile when starting the application.
    
    -   By using this feature, you can define property files like application-(profile).xml.
    In this demo project you can find sample usage in resource folder.
     
 -  [Springboot LogBack extensions](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-logging.html#boot-features-logback-extensions) provide profile specific configuration.
  
    -   So you don't need to make any changes about logging level while deploying your application to production environment.
    You can find sample usage in logback-spring.xml file in the resource folder.
    
 -  Springboot recommend that you [locate main class](http://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-structuring-your-code.html#using-boot-locating-the-main-class) in a root package above other classes.
    
    -   Using a root package also allows the @ComponentScan annotation to be used without needing to specify a basePackage attribute.
    You can find sample usage in `DemoProjectApplication.class`.
    
###Consul Notes 
   Installation of consul is really easy. You can follow steps from [here](https://www.consul.io/intro/getting-started/install.html) .
   
 -  To run consul agent, you can follow steps from [here](https://www.consul.io/intro/getting-started/agent.html) .
      
    - For this project, i started the agent via following command.  ui argument enable the web view to track services.(http://localhost:8500/ui)
    ```sh
    $ consul agent -dev -bind 127.0.0.1 -ui
    ```
    
 -  To install **consul-template**, you can use given link as a guide in the section of required tech. 
 In [github page](https://github.com/hashicorp/consul-template#consul-template) , you can find detailed explanation about it.
 
### Integration Consul And SpringBoot
    
  - First, we need to add following dependency. "javax.ws.rs" dependency excluded because of collision with jersey's dependencies
  
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>javax.ws.rs</groupId>
                    <artifactId>jsr311-api</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

  - Then, we need to force spring boot application to act as a service discovery. As you understand, springboot simplifies configuration tasks.
   To enable consul discovery, just we need to mark root class with `@EnableDiscoveryClient`.
   
  - To connect consul agent, we need to define basic information such as host and port.    
   
        spring.cloud.consul.host = 127.0.0.1
        spring.cloud.consul.port = 8500
        #spring.cloud.consul.discovery.healthCheckPath = /consul/health
        spring.cloud.consul.discovery.healthCheckInterval = 100s
  - At the startup, our service can connect to consul agent with given parameters in above. You can check our service status from UI portal.
  First, our service is listed in failing list, after first health check our service will move to passing list if everything goes good:)
   
   
     
   
 
 
    
    
    
 
 
   
    