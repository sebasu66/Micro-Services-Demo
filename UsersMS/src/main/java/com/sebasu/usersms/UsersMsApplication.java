package com.sebasu.usersms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication

public class UsersMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersMsApplication.class, args);
    }

}
