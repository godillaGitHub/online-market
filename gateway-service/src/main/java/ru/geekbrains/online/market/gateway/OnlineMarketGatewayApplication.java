package ru.geekbrains.online.market.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://cloud.spring.io/spring-cloud-gateway/reference/html/

@SpringBootApplication
public class OnlineMarketGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineMarketGatewayApplication.class, args);
    }
}