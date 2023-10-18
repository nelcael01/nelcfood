package com.nelcfood.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.nelcfood.model.entities")
@SpringBootApplication
public class NelcfoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(NelcfoodApplication.class, args);
    }

}
