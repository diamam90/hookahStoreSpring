package net.company.hookahstore.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
//"net.company.hookahstore.service.impl","net.company.hookahstore.filter",
//        "net.company.hookahstore.listener"
@Configuration
@ComponentScan({"net.company.hookahstore.service.impl",
        "net.company.hookahstore.controller",
        })
public class ServiceConfig {

    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
        PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
        conf.setLocations(getResources());
        return conf;
    }

    private static Resource[] getResources(){
        return new Resource[] {new ClassPathResource("application.properties")};
    }
}
