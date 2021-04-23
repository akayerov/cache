package com.akayerov.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CasheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasheApplication.class, args);
        System.out.println("Hello word");
    }

}
