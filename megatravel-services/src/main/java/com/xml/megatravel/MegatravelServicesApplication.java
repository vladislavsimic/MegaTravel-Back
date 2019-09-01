package com.xml.megatravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MegatravelServicesApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MegatravelServicesApplication.class, args);
    }

}
