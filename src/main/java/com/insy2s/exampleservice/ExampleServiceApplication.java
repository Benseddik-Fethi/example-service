package com.insy2s.exampleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleServiceApplication {

    private ExampleServiceApplication() {
    }

    public static void main(final String[] args) {
        SpringApplication.run(ExampleServiceApplication.class, args);
    }

}
