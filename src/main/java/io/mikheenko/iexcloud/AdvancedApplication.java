package io.mikheenko.iexcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@ConfigurationPropertiesScan
public class AdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedApplication.class, args);
    }

}