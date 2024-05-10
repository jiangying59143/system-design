package com.my.springbootnativeimage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootNativeImageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNativeImageApplication.class, args);
    }

    @GetMapping
    public String hello(){
        return "hello test spring boot native image";
    }
}
