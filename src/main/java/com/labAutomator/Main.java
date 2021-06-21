package com.labAutomator;

import com.labAutomator.Helpers.RequestLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.setProperty("server.port", "8083");
        SpringApplication.run(Main.class, args);
        RequestLogger.getInstance().logRequest("abcdef");
    }

}