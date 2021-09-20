package com.Analytics;

import com.Analytics.controller.AnalyticsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Analytics {

    @Autowired
    private AnalyticsController controller;

    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(Analytics.class, args);
    }


}
