package com.hospital.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hospital")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.hospital")
public class HospManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospManageApplication.class, args);
    }
}
